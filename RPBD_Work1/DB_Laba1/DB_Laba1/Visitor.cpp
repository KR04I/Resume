#include "Visitor.h"

Visitor::Visitor()
{
}

Visitor::~Visitor()
{
}

int Visitor::getId () const
{
	return this->id;
}

void Visitor::setId(int id)
{
	this->id = id;
}

int Visitor::getPassport_id() const
{
	return this->passport_id;
}

void Visitor::setPassport_id(int passport_id)
{
	this->passport_id = passport_id;
}

string Visitor::getName() const
{
	return this->name;
}

void Visitor::setName(const string& name)
{
	this->name = name;
}

string Visitor::getSurname() const
{
	return this->surname;
}

void Visitor::setSurname(const string& surname)
{
	this->surname = surname;
}

string Visitor::getPatronymic() const
{
	return this->patronymic;
}

void Visitor::setPatronymic(const string& patronymic)
{
	this->patronymic = patronymic;
}

bool Visitor::getGender() const
{
	return this->gender;
}

void Visitor::setGender(bool gender)
{
	this->gender = gender;
}



Date Visitor::getDate() const
{
	return this->birthday;
}

void Visitor::setDate(Date date)
{
	this->birthday.day = date.day;
	this->birthday.month = date.month;
	this->birthday.year = date.year;
}

std::string Visitor::toStringDate() const
{
	return std::to_string(this->birthday.year) +
		"-" + std::to_string(this->birthday.month) +
		"-" + std::to_string(this->birthday.day);
}

Date Visitor::setStringDate(std::string& date)
{
	Date strDate;
	std::stringstream in(date);
	std::getline(in, date, '-');
	strDate.year = stoi(date);
	std::getline(in, date, '-');
	strDate.month = stoi(date);
	std::getline(in, date, '-');
	strDate.day = stoi(date);

	return strDate;
}