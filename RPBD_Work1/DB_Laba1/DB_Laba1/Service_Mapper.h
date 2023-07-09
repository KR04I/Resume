#pragma once
#ifndef Service_Mapper_HEADER
#define Service_Mapper_HEADER

#include "BaseMapper.h"
#include "Service.h"

#define ID_LEN 8
#define NAME_LEN 1024

class Service_Mapper : public BaseMapper<Service, int>
{
private:
	SQLINTEGER id;
	//SQLCHAR name[256];
	SQLCHAR name[NAME_LEN];
	SQLINTEGER price;

public:
	Service_Mapper(DataBase& db);
	~Service_Mapper();

	void save(const Service& service) override;
	void remove(int id) override;
	Service* findById(int id) override;
	std::vector<Service*> findAll() override;
};
#endif
