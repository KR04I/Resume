#pragma once
#ifndef Service_HEADER
#define Service_HEADER

#include <iostream>
#include <string>

using namespace std;

class Service
{
private:
	int id;
	int price;
	string name;

public:
	Service();
	~Service();

	int getId() const;
	void setId(int);

	int getPrice() const;
	void setPrice(int);

	string getName() const;
	void setName(const string& name);

};
#endif
