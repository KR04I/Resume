#include "DataBase.h"

void DataBase::connect()
{
	SQLWCHAR name[] = L"PostgreSQL30";
	SQLWCHAR user_name[] = L"visitor";
	SQLWCHAR pass[] = L"12345";
	if (SQLAllocHandle(SQL_HANDLE_ENV, SQL_NULL_HANDLE, &environment) == SQL_ERROR)
	{
		fwprintf(stderr, L"Unable to allocate an environment handle\n");
		exit(-1);
	}

	if (SQLSetEnvAttr(environment, SQL_ATTR_ODBC_VERSION, (SQLPOINTER)SQL_OV_ODBC3, 0) == SQL_ERROR) {
		fwprintf(stderr, L"Unable to set an environment variable\n");
		exit(-1);
	}

	if (SQLAllocHandle(SQL_HANDLE_DBC, environment, &connection) == SQL_ERROR) {
		fwprintf(stderr, L"Unable to allocate an connection handle\n");
		exit(-1);
	}

	if (SQLConnect(connection, name, SQL_NTS, user_name, SQL_NTS, pass, SQL_NTS) == SQL_ERROR) {
		fwprintf(stderr, L"Unable to connect\n");
		exit(-1);
	}

	if (SQLAllocHandle(SQL_HANDLE_STMT, connection, &statement) == SQL_ERROR) {
		fwprintf(stderr, L"Unable to allocate stmt handle\n");
		exit(-1);
	}
}

DataBase::DataBase() {
	connect();
	createTables();
}

DataBase::~DataBase()
{
	if (this->statement != SQL_NULL_HSTMT)
		SQLFreeHandle(SQL_HANDLE_STMT, this->statement);

	if (this->connection != SQL_NULL_HDBC) {
		SQLDisconnect(this->connection);
		SQLFreeHandle(SQL_HANDLE_DBC, this->connection);
	}

	if (this->environment != SQL_NULL_HENV)
		SQLFreeHandle(SQL_HANDLE_ENV, this->environment);
}


const wchar_t* DataBase::getWChar(const char* c)
{
	const size_t cSize = strlen(c) + 1;
	wchar_t* wc = new wchar_t[cSize];
	mbstowcs(wc, c, cSize);
	return wc;
}

void DataBase::createTables()
{
	SQLRETURN ret;
	ret = SQLExecDirect(statement,
		(SQLWCHAR*)getWChar("CREATE TABLE IF NOT EXISTS room(\
			id serial primary key,\
			room_number integer,\
			level integer,\
			number_available_seats integer,\
			living_people integer\
	)"), SQL_NTS);
	ret = SQLExecDirect(statement,
		(SQLWCHAR*)getWChar("CREATE TABLE IF NOT EXISTS passport(\
			id serial primary key,\
			address varchar(50),\
			number integer,\
			date_extradition date,\
			passport_issuance varchar(50)\
		)"), SQL_NTS);
	ret = SQLExecDirect(statement,
		(SQLWCHAR*)getWChar("CREATE TABLE if not exists visitor(\
			id serial primary key,\
			passport_id int references passport(id),\
			name varchar(50),\
			surname varchar(50),\
			patronymic varchar(50),\
			gender int,\
			birthday date"), SQL_NTS);
	ret = SQLExecDirect(statement,
		(SQLWCHAR*)getWChar("CREATE TABLE IF NOT EXISTS registration(\
			id serial primary key,\
			visitor_id integer references visitor(id),\
			room_number integer references room(id),\
			parking_number integer,\
			car_registration_number varchar(50),\
			date_of_entry date,\
			date_of_departure date"), SQL_NTS);
	ret = SQLExecDirect(statement,
		(SQLWCHAR*)getWChar("CREATE TABLE IF NOT EXISTS service(\
			id serial primary key,\
			name varchar(50),\
			price float"), SQL_NTS);
	ret = SQLExecDirect(statement,
		(SQLWCHAR*)getWChar("CREATE TABLE IF NOT EXISTS visitor_service(\
			service_id integer references service(id),\
			visitor_id integer references visitor(id),\
			quantity integer"), SQL_NTS);
	ret = SQLExecDirect(statement,
		(SQLWCHAR*)getWChar("CREATE TABLE IF NOT EXISTS staff(\
			id serial primary key,\
			passport_id integer references passport(id),\
			schedule timestamp,\
			name varchar(50),\
			surname varchar(50),\
			patronymic varchar(50)"), SQL_NTS);
}

const SQLHSTMT& DataBase::execute(const char* sql)
{
	SQLRETURN ret = SQLExecDirect(statement,
		(SQLWCHAR*)getWChar(sql), SQL_NTS);
	if (ret == SQL_SUCCESS) {
		return this->statement;
	}
	/*if (ret == SQL_SUCCESS_WITH_INFO) cout << "SQL_SUCCESS_WITH_INFO" << endl;
	else if (ret == SQL_NEED_DATA) cout << "SQL_NEED_DATA" << endl;
	else if (ret == SQL_STILL_EXECUTING) cout << "SQL_STILL_EXECUTING" << endl;
	else if (ret == SQL_ERROR) cout << "SQL_ERROR" << endl;
	else if (ret == SQL_NO_DATA) cout << "SQL_NO_DATA" << endl;
	else if (ret == SQL_INVALID_HANDLE) cout << "SQL_INVALID_HANDLE" << endl;
	else cout << "return value not found" << endl;*/

	return nullptr;
}