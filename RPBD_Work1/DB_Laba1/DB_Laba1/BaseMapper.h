#pragma once
#ifndef BaseMapper_HEADER
#define BaseMapper_HEADER

#include "DataBase.h"
#include <vector>

template<class T, class TypeID>
class BaseMapper {
protected:
	DataBase& db;

public:
	BaseMapper(DataBase& db);
	virtual void save(const T& date) = 0;
	virtual void remove(TypeID typeID) = 0;
	virtual T* findById(TypeID typeID) = 0;
	virtual std::vector<T*> findAll() = 0;
};

template<class T, class TypeID>
BaseMapper<T, TypeID>::BaseMapper(DataBase& db) : db(db) {}

#endif