#pragma once
#ifndef room_mapper_HEADER
#define room_mapper_HEADER

//#include "DataBase.h"
#include "BaseMapper.h"
#include "Room.h"

#define ID_LEN 10
#define NAME_LEN 1024

class Room_Mapper : public BaseMapper<Room,int>
{
private:
	SQLINTEGER id;
	SQLINTEGER room_number;
	SQLINTEGER level;
	SQLINTEGER number_available_seats;
	SQLINTEGER living_people;


public:
	Room_Mapper(DataBase& db);
	~Room_Mapper();

	void save(const Room& room) override;
	void remove(int id) override;
	Room* findById(int id) override;
	vector<Room*> findAll() override;

	//void addRoom(int id, int room_number, int level, int number_available_seats, int living_people);
	//void removeRoom(int id, int room_number, int level, int number_available_seats, int living_people);
	
};
#endif
