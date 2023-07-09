#include "Passport.h"

Passport::Passport()
{
}

Passport::~Passport()
{
}

int Passport::getId() const
{
	return this->id;
}

void Passport::setId(int id)
{
	this->id = id;
}

int Passport::getNumber() const
{
	return this->number;
}

void Passport::setNumber(int number)
{
	this->number = number;
}

string Passport::getAddress() const
{
	return this->address;
}

void Passport::setAddress(const string& address)
{
	this->address = address;
}

string Passport::getPassport_issuance() const
{
	return this->passport_issuance;
}

void Passport::setPassport_issuance(const string& passport_issuance)
{
	this->passport_issuance = passport_issuance;
}



Date Passport::getDate() const
{
	return this->date_extradition;
}

void Passport::setDate(Date date)
{
	this->date_extradition.day = date.day;
	this->date_extradition.month = date.month;
	this->date_extradition.year = date.year;
}

string Passport::toStringDate() const
{
	return std::to_string(this->date_extradition.year) +
		"-" + std::to_string(this->date_extradition.month) +
		"-" + std::to_string(this->date_extradition.day);
}

Date Passport::setStringDate(std::string& date)
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