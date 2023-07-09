#include "Visitor_Mapper.h"
#include "Storage.h"


Visitor_Mapper::Visitor_Mapper(DataBase& db, Service_Mapper& service_mapper) : BaseMapper(db), service_mapper(service_mapper)
{
}

Visitor_Mapper::~Visitor_Mapper()
{
}

void Visitor_Mapper::save(const Visitor& visitor)
{
	char buf[1024];
	if (visitor.getId() != -1) {
		sprintf(buf,
			"UPDATE visitor SET passport_id = '%d', \"name\" = '%s', surname = '%s', patronymic = '%s', gender = '%d', birthday = '%s' WHERE id = '%d'",
			visitor.getPassport_id(),
			visitor.getName().c_str(),
			visitor.getSurname().c_str(),
			visitor.getPatronymic().c_str(),
			visitor.getGender(),
			visitor.toStringDate().c_str(),
			visitor.getId());
	}
	else {
		sprintf(buf, "INSERT INTO visitor(passport_id, name, surname, patronymic, gender, birthday) VALUES ('%d', '%s', '%s', '%s', '%d', '%s')",
			visitor.getPassport_id(),
			visitor.getName().c_str(),
			visitor.getSurname().c_str(),
			visitor.getPatronymic().c_str(),
			visitor.getGender(),
			visitor.toStringDate().c_str());
	}
	db.execute(buf);
}

void Visitor_Mapper::remove(int id)
{
	char buf[64];
	sprintf(buf, "DELETE FROM visitor WHERE id = %d", id);
	db.execute(buf);
}

Visitor* Visitor_Mapper::findById(int id)
{
	char buf[64];
	sprintf(buf, "SELECT vi.id, vi.passport_id, vi.name, vi.surname, vi.patronymic, vi.birthday FROM visitor AS vi WHERE vi.id = %d", id);

	SQLHSTMT statement = db.execute(buf);
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &this->id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_LONG, &this->passport_id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_CHAR, &this->name, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_CHAR, &this->surname, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_CHAR, &this->paronymic, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 6, SQL_C_CHAR, &this->birthday, NAME_LEN, nullptr);

	retcode = SQLFetch(statement);
	if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
		Visitor visitor;
		visitor.setId(id);
		visitor.setPassport_id(passport_id);
		visitor.setName(reinterpret_cast<char*>(name));
		visitor.setSurname(reinterpret_cast<char*>(surname));
		visitor.setPatronymic(reinterpret_cast<char*>(paronymic));
		string str = reinterpret_cast<char*>(birthday);
		SQLFreeStmt(statement, SQL_CLOSE);
		return Storage::getInstance()->getVisitor(visitor.getId());
	}
	else if (retcode != SQL_NO_DATA) {
		std::cout << "SearchById error in order_mapper\n";
	}
	return nullptr;
}

vector<Visitor*> Visitor_Mapper::findAll()
{
	std::vector<Visitor*> Visitor_vector;

	SQLHSTMT statement = db.execute("SELECT vi.id, vi.passport_id, vi.name, vi.surname, vi.patronymic, vi.gender, vi.birthday FROM visitor AS vi ORDER BY vi.id ASC");
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_LONG, &passport_id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_CHAR, &name, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_CHAR, &surname, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_CHAR, &paronymic, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 6, SQL_C_LONG, &gender, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 7, SQL_C_CHAR, &birthday, NAME_LEN, nullptr);

	while (true) 
	{
		retcode = SQLFetch(statement);
		if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
		Visitor visitor;
		visitor.setId(id);
		visitor.setPassport_id(passport_id);
		visitor.setName(reinterpret_cast<char*>(name));
		visitor.setSurname(reinterpret_cast<char*>(surname));
		visitor.setPatronymic(reinterpret_cast<char*>(paronymic));
		visitor.setGender(gender);
		string str = reinterpret_cast<char*>(birthday);
		visitor.setDate(visitor.setStringDate(str));
		Storage::getInstance()->putVisitor(visitor);
		Visitor_vector.push_back(Storage::getInstance()->getVisitor(visitor.getId()));
	}
		else {
			if (retcode != SQL_NO_DATA) {
				std::cout << "Error fetching genres\n";
			}
			break;
		}
	}
	SQLFreeStmt(statement, SQL_CLOSE);
	return Visitor_vector;
}

void Visitor_Mapper::addService(int service_id, int visitor_id, int quantity)
{
	char buf[128];
	sprintf(buf, "INSERT INTO visitor_service(service_id, visitor_id, quantity) values (%d, %d, %d)", service_id, visitor_id, quantity);
	db.execute(buf);
}

void Visitor_Mapper::removeService(int service_id, int visitor_id, int quantity)
{
	char buf[128];
	sprintf(buf, "DELETE FROM visitor_service WHERE (service_id = %d AND visitor_id = %d AND quantity = %d)", service_id, visitor_id, quantity);
	db.execute(buf);
}
