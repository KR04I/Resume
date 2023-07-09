#pragma once
#ifndef Staff_HEADER
#define Staff_HEADER

#include <iostream>
#include <string>

using namespace std;

class Staff
{
private:
	int id;
	int passport_id;
	string name;
	string surname;
	string patronymic;

public:
	Staff();
	~Staff();

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
};
#endif

