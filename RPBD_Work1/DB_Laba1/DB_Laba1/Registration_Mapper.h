#pragma once
#ifndef Registration_Mapper_HEADER
#define Registration_Mapper_HEADER

#include "BaseMapper.h"
#include "Registration.h"

#define ID_LEN 8
#define NAME_LEN 1024

class Registration_Mapper : public BaseMapper<Registration, int>
{
private:
	SQLINTEGER id;
	SQLINTEGER visitor_id;
	SQLINTEGER room_number;
	SQLINTEGER parking_number;
	SQLCHAR car_registration_number[50];
	SQLCHAR date_of_entry[32];
	SQLCHAR date_of_departure[32];
public:
	Registration_Mapper(DataBase& db);
	~Registration_Mapper();

	void save(const Registration& registration) override;
	void remove(int id) override;
	Registration* findById(int id) override;
	vector<Registration*> findAll() override;
};
#endif
