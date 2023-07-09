#pragma once
#ifndef Visitor_HEADER
#define Visitor_HEADER

#include <iostream>
#include <string>
#include <sstream>
#include "Date.h"

using namespace std;

class Visitor
{
private:
	int id;
	int passport_id;
	bool gender;
	string name;
	string surname;
	string patronymic;
	Date birthday;

public:
	Visitor();
	~Visitor();

	int getId() const;
	void setId(int);

	int getPassport_id() const;
	void setPassport_id(int);

	string getName() const;
	void setName(const string& name);

	string getSurname() const;
	void setSurname(const string& surname);

	string getPatronymic() const;
	void setPatronymic(const string& patronymic);

	bool getGender() const;
	void setGender(bool);

	Date getDate() const;
	void setDate(Date);

	std::string toStringDate() const;
	Date setStringDate(std::string& date);
};
#endif