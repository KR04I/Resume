#pragma once
#ifndef Passport_Mapper_HEADER
#define Passport_Mapper_HEADER
#include "BaseMapper.h"
#include "Passport.h"

#define ID_LEN 8
#define NAME_LEN 1024

class Passport_Mapper : public BaseMapper<Passport,int>
{
private:
	SQLINTEGER id;
	SQLINTEGER number;
	SQLCHAR address[64];
	SQLCHAR passport_issuance[128];
	SQLCHAR date_extradition[64];
public:
	Passport_Mapper(DataBase& db);
	~Passport_Mapper();

	void save(const Passport& passport) override;
	void remove(int id) override;
	Passport* findById(int id) override;
	std::vector<Passport*> findAll() override;
};
#endif

