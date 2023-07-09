#include "Room_Mapper.h"
#include "Storage.h"

Room_Mapper::Room_Mapper(DataBase& db) : BaseMapper(db)
{
}

Room_Mapper :: ~Room_Mapper()
{
}

void Room_Mapper::save(const Room& room)
{
	char buf[1024];
	if (room.getId() != -1)
	{
		sprintf(buf,
			"UPDATE room SET room_number = '%d', level = '%d', number_available_seats = '%d', living_people = '%d' WHERE id = '%d'",
			room.getRoom_number(),
			room.getLevel(),
			room.getNumber_available_seats(),
			room.getLiving_people(),
			room.getId());
	}
	else 
	{
		sprintf(buf, "INSERT INTO room(room_number, level, number_available_seats, living_people) VALUES ('%d', '%d', '%d', '%d')",
			room.getRoom_number(),
			room.getLevel(),
			room.getNumber_available_seats(),
			room.getLiving_people());
	}
	db.execute(buf);
	
}

void Room_Mapper::remove(int id)
{
	char buf[64];
	sprintf(buf, "DELETE FROM room as r WHERE r.id = %d", id);
	db.execute(buf);
}

Room* Room_Mapper::findById(int id)
{
	char buf[64];
	sprintf(buf, "SELECT r.id, r.room_number, r.level, r.number_available_seats, living_people FROM room AS r WHERE r.id = %d, id");

	SQLHSTMT statement = db.execute(buf);
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &this->id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_LONG, &this->room_number,NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_LONG, &this->level, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_LONG, &this->number_available_seats, NAME_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_LONG, &this->living_people, NAME_LEN, nullptr);

	retcode = SQLFetch(statement);
	if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO)
	{
		Room room;
		room.setId(id);
		room.setRoom_number(room_number);
		room.setLevel(level);
		room.setNumber_available_seats(number_available_seats);
		room.setLiving_people(living_people);
		SQLFreeStmt(statement, SQL_CLOSE);
		return Storage::getInstance()->getRoom(room.getId());
	}
	else if (retcode != SQL_NO_DATA)
	{
		cout << "SearchById error in room_mapper\n";
	}
	return nullptr;
}

std::vector<Room*> Room_Mapper::findAll()
{
	vector<Room*> room_vector;
	
	SQLHSTMT statement = db.execute("SELECT r.id, r.room_number, r.level, r.number_available_seats, r.living_people FROM room AS r ORDER BY r.id ASC");
	SQLRETURN retcode;
	retcode = SQLBindCol(statement, 1, SQL_C_LONG, &id, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 2, SQL_C_LONG, &room_number, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 3, SQL_C_LONG, &level, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 4, SQL_C_LONG, &number_available_seats, ID_LEN, nullptr);
	retcode = SQLBindCol(statement, 5, SQL_C_LONG, &living_people, ID_LEN, nullptr);

	while (true)
	{
		retcode = SQLFetch(statement);
		if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO)
		{
			Room room;
			room.setId(id);
			room.setRoom_number(room_number);
			room.setLevel(level);
			room.setNumber_available_seats(number_available_seats);
			room.setLiving_people(living_people);

			Storage::getInstance()->putRoom(room);
			room_vector.push_back(Storage::getInstance()->getRoom(room.getId()));
		}
		else
		{
			if (retcode != SQL_NO_DATA)
			{
				cout << "Error fetching ..._mapper\n";
			}
			break;
		}
	}
	SQLFreeStmt(statement, SQL_CLOSE);
	return room_vector;
}