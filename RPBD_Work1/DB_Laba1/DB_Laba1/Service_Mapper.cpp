#include "Service_Mapper.h"
#include "Storage.h"

Service_Mapper::Service_Mapper(DataBase& db) : BaseMapper(db)
{
}

Service_Mapper::~Service_Mapper()
{
}

void Service_Mapper::save(const Service& service)
{
	char buf[1024];
	if (service.getId() != -1) {
		sprintf(buf,
			"UPDATE service SET name = '%s', price = '%d' WHERE id = '%d'",
			service.getName().c_str(),
			service.getPrice(),
			service.getId());
	}
	else {
		sprintf(buf, "INSERT INTO service(name, price) VALUES ('%s','%d')",
			service.getName().c_str(),
			service.getPrice());
	}
	db.execute(buf);
}

void Service_Mapper::remove(int id)
{
	char buf[64];
	sprintf(buf, "DELETE FROM service as se WHERE se.id = %d", id);
	db.execute(buf);
}

Service* Service_Mapper::findById(int id)
{
	char buf[64];
	sprintf(buf, "SELECT se.id, se.name, se.price FROM service AS se WHERE se.id = %d", id);

	SQLHSTMT statement = db.execute(buf);
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &this->id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_CHAR, &this->name, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_LONG, &this->price, NAME_LEN, nullptr);

	retcode = SQLFetch(statement);
	if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
		Service service;
		service.setId(id);
		service.setName(reinterpret_cast<char*>(name));
		service.setPrice(price);
		SQLFreeStmt(statement, SQL_CLOSE);
		return Storage::getInstance()->getService(service.getId());
	}
	else if (retcode != SQL_NO_DATA) {
		std::cout << "SearchById error in order_mapper\n";
	}
	return nullptr;
}

std::vector<Service*> Service_Mapper::findAll()
{
	std::vector<Service*> service_vector;

	SQLHSTMT statement = db.execute("SELECT se.id, se.name, se.price FROM service AS se ORDER BY se.id ASC");
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_CHAR, &name, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_LONG, &price, NAME_LEN, nullptr);

	while (true) {
		retcode = SQLFetch(statement);
		if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
			Service service;
			service.setId(id);
			service.setName(reinterpret_cast<char*>(name));
			service.setPrice(price);

			//materials_vector.push_back(&materials);

			Storage::getInstance()->putService(service);
			service_vector.push_back(Storage::getInstance()->getService(service.getId()));
		}
		else {
			if (retcode != SQL_NO_DATA) {
				std::cout << "Error fetching client_mapper\n";
			}
			break;
		}
	}
	SQLFreeStmt(statement, SQL_CLOSE);
	return service_vector;
}