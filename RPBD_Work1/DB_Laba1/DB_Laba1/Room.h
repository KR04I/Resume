#pragma once

#ifndef Room_HEADER
#define Room_HEADER

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

class Room
{
private:
	int id;
	int room_number;
	int level;
	int number_available_seats;
	int living_people;

public:
	Room();
	~Room();

	int getId() const;
	void setId(int);

	int getRoom_number() const;
	void setRoom_number(int);

	int getLevel() const;
	void setLevel(int);

	int getNumber_available_seats() const;
	void setNumber_available_seats(int);

	int getLiving_people() const;
	void setLiving_people(int);

};
#endif

