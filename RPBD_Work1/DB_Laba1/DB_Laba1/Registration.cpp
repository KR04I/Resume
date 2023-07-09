#include "Registration.h"

Registration::Registration()
{
}

Registration::~Registration()
{
}

int Registration::getId() const
{
	return this->id;
}

void Registration::setId(int id)
{
	this->id = id;
}

int Registration::getVisitor_id() const
{
	return this->visitor_id;
}

void Registration::setVisitor_id(int visitor_id)
{
	this->visitor_id = visitor_id;
}

int Registration::getRoom_number() const
{
	return this->room_number;
}

void Registration::setRoom_number(int room_number)
{
	this->room_number = room_number;
}

int Registration::getParking_number() const
{
	return this->parking_number;
}

void Registration::setParking_number(int parking_number)
{
	this->parking_number = parking_number;
}

string Registration::getCar_registration_number() const
{
	return this->car_registration_number;
}

void Registration::setCar_registration_number(const string& car_registration_number)
{
	this->car_registration_number = car_registration_number;
}

Date Registration::getDateOfEntry() const
{
	return this->date_of_entry;
}

void Registration::setDateOfEntry(Date Date)
{
	this->date_of_entry.day = Date.day;
	this->date_of_entry.month = Date.month;
	this->date_of_entry.year = Date.year;
}

string Registration::toStringDateOfEntry() const
{
	return std::to_string(this->date_of_entry.year) +
		"-" + std::to_string(this->date_of_entry.month) +
		"-" + std::to_string(this->date_of_entry.day);
}

Date Registration::setStringDateOfEntry(std::string& date)
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

Date Registration::getDateOfDeparture() const
{
	return this->date_of_departure;
}

void Registration::setDateOfDeparture(Date Date1)
{
	this->date_of_departure.day = Date1.day;
	this->date_of_departure.month = Date1.month;
	this->date_of_departure.year = Date1.year;
}

string Registration::toStringDateOfDeparture() const
{
	return std::to_string(this->date_of_departure.year) +
		"-" + std::to_string(this->date_of_departure.month) +
		"-" + std::to_string(this->date_of_departure.day);
}

Date Registration::setStringDateOfDeparture(std::string& date1)
{
	Date strDate1;
	std::stringstream in(date1);
	std::getline(in, date1, '-');
	strDate1.year = stoi(date1);
	std::getline(in, date1, '-');
	strDate1.month = stoi(date1);
	std::getline(in, date1, '-');
	strDate1.day = stoi(date1);

	return strDate1;
}