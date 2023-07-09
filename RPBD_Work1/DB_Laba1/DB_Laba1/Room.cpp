#include "Room.h"

Room::Room() : id(-1), room_number(), level(), number_available_seats(), living_people()
{
}

Room::~Room()
{
}

int Room::getId() const
{
	return this->id;
}

void Room::setId(int id)
{
	this->id = id;
}

int Room::getLevel() const
{
	return this->level;
}

void Room::setLevel(int level)
{
	this->level = level;
}

int Room::getLiving_people() const
{
	return this->living_people;
}

void Room::setLiving_people(int living_people)
{
	this->living_people = living_people;
}

int Room::getNumber_available_seats() const
{
	return this->number_available_seats;
}

void Room::setNumber_available_seats(int number_available_seats)
{
	this->number_available_seats = number_available_seats;
}

int Room::getRoom_number() const
{
	return this->room_number;
}

void Room::setRoom_number(int room_number)
{
	this->room_number = room_number;
}
