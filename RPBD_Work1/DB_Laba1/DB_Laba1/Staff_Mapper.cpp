#include "Staff_Mapper.h"
#include "Storage.h"

Staff_Mapper::Staff_Mapper(DataBase& db) : BaseMapper(db)
{
}

Staff_Mapper::~Staff_Mapper()
{
}

void Staff_Mapper::save(const Staff& staff)
{
	char buf[1024];
	if (staff.getId() != -1) {
		sprintf(buf,
			"UPDATE staff SET passport_id = '%d', name = '%s', surname = '%s', patronymic = '%s' WHERE id = '%d'",
			staff.getPassport_id(),
			staff.getName().c_str(),
			staff.getSurname().c_str(),
			staff.getPatronymic().c_str(),
			staff.getId());
	}
	else {
		sprintf(buf, "INSERT INTO staff(passport_id, name, surname, patronymic) VALUES ('%d', '%s', '%s','%s')",
			staff.getPassport_id(),
			staff.getName().c_str(),
			staff.getSurname().c_str(),
			staff.getPatronymic().c_str());
	}
	db.execute(buf);
}

void Staff_Mapper::remove(int id)
{
	char buf[64];
	sprintf(buf, "DELETE FROM staff as st WHERE st.id = %d", id);
	db.execute(buf);
}

Staff* Staff_Mapper::findById(int id)
{
	char buf[64];
	sprintf(buf, "SELECT st.id, st.passport_id, st.name, st.surname, st.patronymic FROM staff AS st WHERE st.id = %d", id);

	SQLHSTMT statement = db.execute(buf);
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &this->id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_LONG, &this->passport_id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_CHAR, &this->name, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_CHAR, &this->surname, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_CHAR, &this->patronymic, NAME_LEN, nullptr);

	retcode = SQLFetch(statement);
	if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
		Staff staff;
		staff.setId(id);
		staff.setPassport_id(passport_id);
		staff.setName(reinterpret_cast<char*>(name));
		staff.setSurname(reinterpret_cast<char*>(surname));
		staff.setPatronymic(reinterpret_cast<char*>(patronymic));
		SQLFreeStmt(statement, SQL_CLOSE);
		return Storage::getInstance()->getStaff(staff.getId());
	}
	else if (retcode != SQL_NO_DATA) {
		std::cout << "SearchById error in moving_mapper\n";
	}
	return nullptr;
}

vector<Staff*> Staff_Mapper::findAll()
{
	std::vector<Staff*> staff_vector;

	SQLHSTMT statement = db.execute("SELECT st.id, st.passport_id, st.name, st.surname, st.patronymic FROM staff AS st ORDER BY st.id ASC");
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_LONG, &passport_id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_CHAR, &name, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_CHAR, &surname, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_CHAR, &patronymic, NAME_LEN, nullptr);

	while (true) {
		retcode = SQLFetch(statement);
		if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
			Staff staff;
			staff.setId(id);
			staff.setPassport_id(passport_id);
			staff.setName(reinterpret_cast<char*>(name));
			staff.setSurname(reinterpret_cast<char*>(surname));
			staff.setPatronymic(reinterpret_cast<char*>(patronymic));
			Storage::getInstance()->putStaff(staff);
			staff_vector.push_back(Storage::getInstance()->getStaff(staff.getId()));
		}
		else {
			if (retcode != SQL_NO_DATA) {
				std::cout << "Error fetching genres\n";
			}
			break;
		}
	}
	SQLFreeStmt(statement, SQL_CLOSE);
	return staff_vector;
}