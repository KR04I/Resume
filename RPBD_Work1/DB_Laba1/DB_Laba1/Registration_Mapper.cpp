#include "Registration_Mapper.h"
#include "Storage.h"

Registration_Mapper::Registration_Mapper(DataBase& db) : BaseMapper(db)
{
}

Registration_Mapper::~Registration_Mapper()
{
}

void Registration_Mapper::save(const Registration& registration)
{
	char buf[1024];
	if (registration.getId() != -1) {
		sprintf(buf,
			"UPDATE Registration SET visitor_id = '%d', room_number = '%d', parking_number = '%d', car_registration_number = '%s', date_of_entry = '%s', date_of_departure = '%s' WHERE id = '%d'",
			registration.getVisitor_id(),
			registration.getRoom_number(),
			registration.getParking_number(),
			registration.getCar_registration_number().c_str(),
			registration.toStringDateOfEntry().c_str(),
			registration.toStringDateOfDeparture().c_str(),
			registration.getId());
	}
	else {
		sprintf(buf, "INSERT INTO registration(visitor_id, room_number, parking_number, car_registration_number, date_of_entry, date_of_departure) VALUES ('%d', '%d', '%d', '%s', '%s', '%s')",
			registration.getVisitor_id(),
			registration.getRoom_number(),
			registration.getParking_number(),
			registration.getCar_registration_number().c_str(),
			registration.toStringDateOfEntry().c_str(),
			registration.toStringDateOfDeparture().c_str());
	}
	db.execute(buf);

}

void Registration_Mapper::remove(int id)
{
	char buf[64];
	sprintf(buf, "DELETE FROM registration as re WHERE re.id = %d", id);
	db.execute(buf);
}

Registration* Registration_Mapper::findById(int id)
{
	char buf[64];
	sprintf(buf, "SELECT re.id, re.parking_number, re.car_registration_number, re.date_of_entry, re.date_of_departure FROM registration AS re WHERE re.id = %d", id);

	SQLHSTMT statement = db.execute(buf);
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &this->id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_CHAR, &this->parking_number, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_CHAR, &this->car_registration_number, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_CHAR, &this->date_of_entry, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_CHAR, &this->date_of_departure, NAME_LEN, nullptr);

	retcode = SQLFetch(statement);
	if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
		Registration reg;
		reg.setId(id);
		reg.setParking_number(parking_number);
		reg.setCar_registration_number(reinterpret_cast<char*>(car_registration_number));
		string str = reinterpret_cast<char*>(date_of_entry);
		string str1 = reinterpret_cast<char*>(date_of_departure);
		reg.setDateOfEntry(reg.setStringDateOfEntry(str));
		reg.setDateOfDeparture(reg.setStringDateOfDeparture(str1));
		SQLFreeStmt(statement, SQL_CLOSE);
		return Storage::getInstance()->getRegistration(reg.getId());
	}
	else if (retcode != SQL_NO_DATA) {
		cout << "SearchById error in Registration_Mapper\n";
	}
	return nullptr;
}

vector<Registration*> Registration_Mapper::findAll()
{
	std::vector<Registration*> registration_info_vector;

	SQLHSTMT statement = db.execute("SELECT re.id, re.visitor_id, re.room_number, re.parking_number, re.car_registration_number, re.date_of_entry, re.date_of_departure FROM registration AS re ORDER BY re.id ASC");
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_LONG, &visitor_id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_LONG, &room_number, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_LONG, &parking_number, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_CHAR, &car_registration_number, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 6, SQL_C_CHAR, &date_of_entry, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 7, SQL_C_CHAR, &date_of_departure, NAME_LEN, nullptr);

	while (true) {
		retcode = SQLFetch(statement);
		if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
			Registration reg_info;
			reg_info.setId(id);
			reg_info.setVisitor_id(visitor_id);
			reg_info.setRoom_number(room_number);
			reg_info.setParking_number(parking_number);
			reg_info.setCar_registration_number(reinterpret_cast<char*>(car_registration_number));
			string str = reinterpret_cast<char*>(date_of_entry);
			reg_info.setDateOfEntry(reg_info.setStringDateOfEntry(str));
			string str1 = reinterpret_cast<char*>(date_of_departure);
			reg_info.setDateOfDeparture(reg_info.setStringDateOfDeparture(str1));
			Storage::getInstance()->putRegistration(reg_info);
			registration_info_vector.push_back(Storage::getInstance()->getRegistration(reg_info.getId()));
		}
		else {
			if (retcode != SQL_NO_DATA) {
				std::cout << "Error fetching genres\n";
			}
			break;
		}
	}
	SQLFreeStmt(statement, SQL_CLOSE);
	return registration_info_vector;
}