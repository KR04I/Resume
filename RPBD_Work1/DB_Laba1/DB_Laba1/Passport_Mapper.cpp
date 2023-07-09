#include "Passport_Mapper.h"
#include "Storage.h"

using namespace std;

Passport_Mapper::Passport_Mapper(DataBase& db) : BaseMapper(db)
{
}

Passport_Mapper::~Passport_Mapper()
{
}

void Passport_Mapper::save(const Passport& passport)
{
	char buf[1024];
	if (passport.getId() != -1) {
		sprintf(buf,
			"UPDATE passport SET address = '%s', number = '%d', \"date_extradition\" = '%s', passport_issuance = '%s' WHERE id = '%d'",
			passport.getAddress().c_str(),
			passport.getNumber(),
			passport.toStringDate().c_str(),
			passport.getPassport_issuance().c_str(),
			passport.getId());
	}
	else {
		sprintf(buf, "INSERT INTO passport(address, number, date_extradition, passport_issuance) VALUES ('%s', '%d', '%s', '%s')",
			passport.getAddress().c_str(),
			passport.getNumber(),
			passport.toStringDate().c_str(),
			passport.getPassport_issuance().c_str());
	}
	db.execute(buf);

}

void Passport_Mapper::remove(int id)
{
	char buf[64];
	sprintf(buf, "DELETE FROM Passport as pa WHERE pa.id = %d", id);
	db.execute(buf);
}

Passport* Passport_Mapper::findById(int id)
{
	char buf[64];
	sprintf(buf, "SELECT pa.id, pa.address, pa.number, pa.date_extradition, pa.passport_issuance FROM passport AS pa WHERE pa.id = %d", id);

	SQLHSTMT statement = db.execute(buf);
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &this->id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_CHAR, &this->address, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_CHAR, &this->number, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_CHAR, &this->date_extradition, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_CHAR, &this->passport_issuance, NAME_LEN, nullptr);

	retcode = SQLFetch(statement);
	if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) 
	{
		Passport passport;
		passport.setId(id);
		passport.setAddress(reinterpret_cast<char*>(address));
		passport.setNumber(number);
		std::string str = reinterpret_cast<char*>(date_extradition);
		passport.setDate(passport.setStringDate(str));
		passport.setPassport_issuance(reinterpret_cast<char*>(passport_issuance));

		SQLFreeStmt(statement, SQL_CLOSE);
		return Storage::getInstance()->getPassport(passport.getId());
	}
	else if (retcode != SQL_NO_DATA) {
		std::cout << "SearchById error in moving_mapper\n";
	}
	return nullptr;
}

vector<Passport*> Passport_Mapper::findAll()
{
	std::vector<Passport*> passport_vector;

	SQLHSTMT statement = db.execute("SELECT pa.id, pa.address, pa.number, pa.date_extradition, pa.passport_issuance FROM passport AS pa ORDER BY pa.id ASC");
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_CHAR, &address, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_LONG, &number, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_CHAR, &date_extradition, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_CHAR, &passport_issuance, NAME_LEN, nullptr);

	while (true) 
	{
		retcode = SQLFetch(statement);
		if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
			Passport passport;
			passport.setId(id);
			passport.setAddress(reinterpret_cast<char*>(address));
			passport.setNumber(number);
			string str = reinterpret_cast<char*>(date_extradition);
			passport.setDate(passport.setStringDate(str));
			passport.setPassport_issuance(reinterpret_cast<char*>(passport_issuance));
			Storage::getInstance()->putPassport(passport);
			passport_vector.push_back(Storage::getInstance()->getPassport(passport.getId()));
		}
		else {
			if (retcode != SQL_NO_DATA) {
				std::cout << "Error fetching genres\n";
			}
			break;
		}
	}
	SQLFreeStmt(statement, SQL_CLOSE);
	return passport_vector;
}