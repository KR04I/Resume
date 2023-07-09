#include "Staff.h"

Staff::Staff()
{
}

Staff::~Staff()
{
}

int Staff::getId() const
{
	return this->id;
}

void Staff::setId(int id)
{
	this->id = id;
}

int Staff::getPassport_id() const
{
	return this->passport_id;
}

void Staff::setPassport_id(int passport_id)
{
	this->passport_id = passport_id;
}

string Staff::getName() const
{
	return this->name;
}

void Staff::setName(const string& name)
{
	this->name = name;
}

string Staff::getSurname() const
{
	return this->surname;
}

void Staff::setSurname(const string& surname)
{
	this->surname = surname;
}

string Staff::getPatronymic() const
{
	return this->patronymic;
}

void Staff::setPatronymic(const string& patronymic)
{
	this->patronymic = patronymic;
}
