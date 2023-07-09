#pragma once
#ifndef Registration_HEADER
#define Registration_HEADER

#include <iostream>
#include <string>
#include <sstream>
#include "Date.h"

using namespace std;

class Registration
{
private:
	int id;
	int visitor_id;
	int room_number;
	int parking_number;
	string car_registration_number;
	Date date_of_entry;
	Date date_of_departure;

public:
	Registration();
	~Registration();

	int getId() const;
	void setId(int);

	int getVisitor_id() const;
	void setVisitor_id(int);

	int getRoom_number() const;
	void setRoom_number(int);

	int getParking_number() const;
	void setParking_number(int);

	string getCar_registration_number() const;
	void setCar_registration_number(const string& car_registration_number);

	Date getDateOfDeparture() const;
	void setDateOfDeparture(Date);

	string toStringDateOfDeparture() const;
	Date setStringDateOfDeparture(std::string& date1);

	Date getDateOfEntry() const;
	void setDateOfEntry(Date);

	string toStringDateOfEntry() const;
	Date setStringDateOfEntry(std::string& date);
};
#endif
