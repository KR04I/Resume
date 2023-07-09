#include "Service.h"

Service::Service()
{
}

Service::~Service()
{
}

int Service::getId() const
{
	return this->id;
}

void Service::setId(int id)
{
	this->id = id;
}

string Service::getName() const
{
	return this->name;
}

void Service::setName (const string& name)
{
	this->name = name;
}
 
int Service::getPrice()const
{
	return this->price;
}

void Service::setPrice(int price)
{
	this->price = price;
}
