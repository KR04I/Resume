#pragma once
#ifndef Passport_HEADER
#define Passport_HEADER

#include <iostream>
#include <string>
#include <sstream>
#include "Date.h"

using namespace std;

class Passport
{
private:
	int id;
	int number;
	string address;
	string passport_issuance;
	Date date_extradition;

public:
	Passport();
	~Passport();

	int getId() const;
	void setId(int);

	int getNumber() const;
	void setNumber(int);

	string getAddress() const;
	void setAddress(const string& address);

	string getPassport_issuance() const;
	void setPassport_issuance(const string& passport_issuance);

	Date getDate() const;
	void setDate(Date);

	string toStringDate() const;
	Date setStringDate(std::string& date);
};
#endif

