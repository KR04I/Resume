#pragma once
#ifndef Staff_Mapper_HEADER
#define Staff_Mapper_HEADER
#include "BaseMapper.h"
#include "Staff.h"

#define ID_LEN 8
#define NAME_LEN 1024

class Staff_Mapper : public BaseMapper <Staff, int>
{
private:
	SQLINTEGER id;
	SQLINTEGER passport_id;
	SQLCHAR name[64];
	SQLCHAR surname[64];
	SQLCHAR patronymic[64];

public:
	Staff_Mapper(DataBase& db);
	~Staff_Mapper();

	void save(const Staff& staff) override;
	void remove(int id) override;
	Staff* findById(int id) override;
	std::vector<Staff*> findAll() override;
};
#endif
