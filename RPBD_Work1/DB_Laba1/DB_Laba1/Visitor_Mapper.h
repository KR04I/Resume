#pragma once
#ifndef Visitor_Mapper_HEADER
#define Visitor_Mapper_HEADER

#include "BaseMapper.h"
#include "Visitor.h"
#include "Service_Mapper.h"

#define ID_LEN 8
#define NAME_LEN 1024

class Visitor_Mapper : public BaseMapper<Visitor, int>
{
private:
	SQLINTEGER id;
	SQLINTEGER passport_id;
	SQLINTEGER gender;
	SQLCHAR name[64];
	SQLCHAR surname[64];
	SQLCHAR paronymic[64];
	SQLCHAR birthday[64];
	Service_Mapper& service_mapper;

public:
	Visitor_Mapper(DataBase& db, Service_Mapper& service_mapper);
	~Visitor_Mapper();

	void save(const Visitor& visitor) override;
	void remove(int id) override;
	Visitor* findById(int id) override;
	std::vector<Visitor*> findAll() override;

	void addService(int service_id, int visitor_id, int quantity);
	void removeService(int service_id, int visitor_id, int quantity);
};
#endif
