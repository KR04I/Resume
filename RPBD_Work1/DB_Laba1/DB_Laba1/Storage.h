#pragma once
#ifndef Storage_HEADER
#define Storage_HEADER

#include <map>

#include "Room.h"
#include "Registration.h"
#include "Visitor.h"
#include "Passport.h"
#include "Staff.h"
#include "Service.h"

using namespace std;

class Storage
{
private:
	map<int, Room > room;
	map<int, Registration > registration;
	map<int, Visitor > visitor;
	map<int, Passport > passport;
	map<int, Staff > staff;
	map<int, Service > service;

	Storage();
	static Storage* storage;

public:
	Storage(Storage& other) = delete;
	void operator = (Storage&) = delete;
	static Storage* getInstance();
	Room* getRoom(int);
	Registration* getRegistration(int);
	Visitor* getVisitor(int);
	Passport* getPassport(int);
	Staff* getStaff(int);
	Service* getService(int);

	void putRoom(Room& room);
	void putRegistration(Registration& registration);
	void putVisitor(Visitor& visitor);
	void putPassport(Passport& passport);
	void putStaff(Staff& staff);
	void putService(Service& service);
};
#endif
