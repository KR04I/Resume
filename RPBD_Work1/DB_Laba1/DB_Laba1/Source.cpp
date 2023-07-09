#include "Room_Mapper.h"
#include "Registration_Mapper.h"
#include "Visitor_Mapper.h"
#include "Passport_Mapper.h"
#include "Staff_Mapper.h"
#include "Service_Mapper.h"


#include <string>
#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

enum option
{
	NONE_OPT,
	MATERIAL_OPT,
	SPECIALIZATION_OPT
};

void printRoom(const vector<Room*>& room) 
{
	for (int i = 0; i < room.size(); i++) 
	{
		cout << "\t" << i + 1 << ") "
			"Number of room: " << room[i]->getRoom_number() << endl
			<< "\t" << "Level: " << room[i]->getLevel() << endl
			<< "\t" << "Number available seats: " << room[i]->getNumber_available_seats() << endl
			<< "\t" << "living people: " << room[i]->getLiving_people() << endl << endl;
	}
}

void printPassport(const vector<Passport*>& passport) 
{
	for (int i = 0; i < passport.size(); i++) 
	{
		cout << "\t" << i + 1 << ") "
			"Address: " << passport[i]->getAddress() << endl
			<< "\t" << "Number: " << passport[i]->getNumber() << endl
			<< "\t" << "Date extradition: " << passport[i]->toStringDate() << endl
			<< "\t" << "Passport issuance: " << passport[i]->getPassport_issuance() << endl << endl;

	}
}

void printRegistration(const vector<Registration*>& registration) 
{
	for (int i = 0; i < registration.size(); i++) 
	{
		cout << "\t" << i + 1 << ") "
			"Parking number: " << registration[i]->getParking_number() << endl
			<< "\t" << "Car registration number: " << registration[i]->getCar_registration_number() << endl
			<< "\t" << "Date of entry: " << registration[i]->toStringDateOfEntry() << endl
			<< "\t" << "Date of departure: " << registration[i]->toStringDateOfDeparture() << endl << endl;
	}
}

void printStaff(const vector<Staff*>& staff) 
{
	for (int i = 0; i < staff.size(); i++) 
	{
		cout << "\t" << i + 1 << ") "
			"Name: " << staff[i]->getName() << endl
			<< "\t" << "Surname: " << staff[i]->getSurname() << endl
			<< "\t" << "Patronymic: " << staff[i]->getPatronymic() << endl << endl;
	}
}

void printVisitor(const vector<Visitor*>& visitor) 
{
	for (int i = 0; i < visitor.size(); i++) 
	{
		cout << "\t" << i + 1 << ") "
			"Name: " << visitor[i]->getName() << endl
			<< "\t" << "Surname: " << visitor[i]->getSurname() << endl
			<< "\t" << "Patronymic: " << visitor[i]->getPatronymic() << endl
			<< "\t" << "Birthday: " << visitor[i]->toStringDate() << endl << endl;
	}
}

void printService(const vector<Service*>& servece) 
{
	for (int i = 0; i < servece.size(); i++) 
	{
		cout << "\t" << i + 1 << ") "
			"Name: " << servece[i]->getName() << endl
			<< "\t" << "price: " << servece[i]->getPrice() << endl << endl;
	}
}


pair<int, int> choice(const vector<Visitor*>& visitor, const vector<Service*>& service) 
{
	int num, num2;
	cout << "Enter visitor number " << endl;
	printVisitor(visitor);
	cin >> num;
		cout << "Enter number of service " << endl;
		printService(service);
		cin >> num2;
	
	return { num, num2 };
}

int roomMenu(int variant, Room& room,
	Room_Mapper& room_mapper, vector<Room*>& rooms) 
{
	int id, room_number, level, number_available_seats, living_people;
	switch (variant)
	{
	case 1:
		printRoom(room_mapper.findAll());
		break;
	case 2:
		cout << "Enter room of number:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		cin >> room_number;

		cout << "Enter  level:" << endl;
		cin >> level;

		cout << "Enter number_available_seats:" << endl;
		cin >> number_available_seats;

		cout << "Enter living people:" << endl;
		cin >> living_people;

		room.setId(-1);
		room.setRoom_number(room_number);
		room.setLevel(level);
		room.setNumber_available_seats(number_available_seats);
		room.setLiving_people(living_people);
		room_mapper.save(room);
		break;

	case 3:
		printRoom(rooms = room_mapper.findAll());
		cout << "Enter room id" << endl;
		cin >> id;

		cout << "Enter new room number:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		cin >> room_number;

		cout << "Enter new level:" << endl;
		cin >> level;

		cout << "Enter new number available seats:" << endl;
		cin >> number_available_seats;

		cout << "Enter new living people:" << endl;
		cin >> living_people;

		room.setId(rooms[id - 1L]->getId());
		room.setRoom_number(room_number);
		room.setLevel(level);
		room.setNumber_available_seats(number_available_seats);
		room.setLiving_people(living_people);
		room_mapper.save(room);
		break;

	case 4:
		printRoom(rooms = room_mapper.findAll());
		cout << "Enter room id" << endl;
		cin >> id;
		room_mapper.remove(rooms[id - 1L]->getId());
		break;

	default:
		break;
	}
	return 0;
}

int staffMenu(int variant, Staff& staff,
	Staff_Mapper& staff_mapper, vector<Staff*>& staffs) {
	string name, surname, patronymic;
	int id, passport_id;
	switch (variant)
	{
	case 1:
		printStaff(staff_mapper.findAll());
		break;
	case 2:
		cout << "Enter name:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, name);

		cout << "Enter surname:" << endl;
		getline(cin, surname);

		cout << "Enter patronymic:" << endl;
		getline(cin, patronymic);

		cout << "Enter passport id:" << endl;
		cin >> passport_id;

		staff.setPassport_id(passport_id);
		staff.setId(-1);
		staff.setName(name);
		staff.setSurname(surname);
		staff.setPatronymic(patronymic);
		staff_mapper.save(staff);
		break;

	case 3:
		printStaff(staffs = staff_mapper.findAll());
		cout << "Enter staff id" << endl;
		cin >> id;

		cout << "Enter new name:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, name);

		cout << "Enter new surname:" << endl;
		getline(cin, surname);

		cout << "Enter new patronymic:" << endl;
		getline(cin, patronymic);


		staff.setId(staffs[id - 1L]->getId());
		staff.setName(name);
		staff.setSurname(surname);
		staff.setPatronymic(patronymic);
		staff_mapper.save(staff);
		break;

	case 4:
		printStaff(staffs = staff_mapper.findAll());
		cout << "Enter staff id" << endl;
		cin >> id;
		staff_mapper.remove(staffs[id - 1L]->getId());
		break;

	default:
		break;
	}
	return 0;
}

int registrationMenu(int variant, Registration& registration,
	Registration_Mapper& registration_mapper, vector<Registration*>& registrations) {
	string car_registration_number, date_of_entry, date_of_departure;
	int id, parking_number, visitor_id, room_number;
	Date date_of_entry_, date_of_departure_;
	switch (variant)
	{
	case 1:
		printRegistration(registration_mapper.findAll());
		break;
	case 2:
		cout << "Enter car registration number:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, car_registration_number);

		cout << "Enter date of entry (year-month-day):" << endl;
		getline(cin, date_of_entry);

		cout << "Enter date of departure (year-month-day):" << endl;
		getline(cin, date_of_departure);

		cout << "Enter parking number:" << endl;
		cin >> parking_number;

		cout << "Enter visitor id :" << endl;
		cin >> visitor_id;

		cout << "Enter room number :" << endl;
		cin >> room_number;


		registration.setVisitor_id(visitor_id);
		registration.setRoom_number(room_number);
		registration.setId(-1);
		registration.setParking_number(parking_number);
		registration.setCar_registration_number(car_registration_number);
		date_of_entry_ = registration.setStringDateOfEntry(date_of_entry);
		registration.setDateOfEntry(date_of_entry_);
		date_of_departure_ = registration.setStringDateOfDeparture(date_of_departure);
		registration.setDateOfDeparture(date_of_departure_);
		registration_mapper.save(registration);
		break;

	case 3:
		printRegistration(registrations = registration_mapper.findAll());
		cout << "Enter registration id" << endl;
		cin >> id;

		cout << "Enter new parking number:" << endl;
		cin >> parking_number;

		cout << "Enter new car registration number:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, car_registration_number);

		cout << "Enter new date of entry (year-month-day):" << endl;
		getline(cin, date_of_entry);

		cout << "Enter new date of departure (year-month-day):" << endl;
		getline(cin, date_of_departure);

		registration.setId(registrations[id - 1L]->getId());
		registration.setParking_number(parking_number);
		registration.setCar_registration_number(car_registration_number);
		date_of_entry_ = registration.setStringDateOfEntry(date_of_entry);
		registration.setDateOfEntry(date_of_entry_);
		date_of_departure_ = registration.setStringDateOfDeparture(date_of_departure);
		registration.setDateOfDeparture(date_of_departure_);
		registration_mapper.save(registration);
		break;

	case 4:
		printRegistration(registrations = registration_mapper.findAll());
		cout << "Enter registration id" << endl;
		cin >> id;
		registration_mapper.remove(registrations[id - 1L]->getId());
		break;

	default:
		break;
	}
	return 0;
}

int passportMenu(int variant, Passport& passport,
	Passport_Mapper& passport_mapper, vector<Passport*>& passports) {
	string address, passport_issuance, date_extradition;
	int id, number;
	Date date_extradition_;
	switch (variant)
	{
	case 1:
		printPassport(passport_mapper.findAll());
		break;
	case 2:
		cout << "Enter address:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, address);

		cout << "Enter passport issuance:" << endl;
		getline(cin, passport_issuance);

		cout << "Enter date(year-month-day):" << endl;
		getline(cin, date_extradition);

		cout << "Enter number:" << endl;
		cin >> number;

		passport.setId(-1);
		passport.setAddress(address);
		passport.setNumber(number);
		passport.setPassport_issuance(passport_issuance);
		date_extradition_ = passport.setStringDate(date_extradition);
		passport.setDate(date_extradition_);
		passport_mapper.save(passport);
		break;

	case 3:
		printPassport(passports = passport_mapper.findAll());
		cout << "Enter passport id" << endl;
		cin >> id;

		cout << "Enter new address:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, address);

		cout << "Enter new passport issuance:" << endl;
		getline(cin, passport_issuance);

		cout << "Enter new date extradition:" << endl;
		getline(cin, date_extradition);

		cout << "Enter new number:" << endl;
		cin >> number;

		passport.setId(passports[id - 1L]->getId());
		passport.setAddress(address);
		passport.setNumber(number);
		passport.setPassport_issuance(passport_issuance);
		date_extradition_ = passport.setStringDate(date_extradition);
		passport.setDate(date_extradition_);
		passport_mapper.save(passport);
		break;

	case 4:
		printPassport(passports = passport_mapper.findAll());
		cout << "Enter passport id" << endl;
		cin >> id;
		passport_mapper.remove(passports[id - 1L]->getId());
		break;

	default:
		break;
	}
	return 0;
}

int visitorMenu(int variant, Visitor& visitor,
	Visitor_Mapper& visitor_mapper, vector<Visitor*>& visitors,
	Service& service, Service_Mapper& service_mapper, vector<Service*>& services) {
	string birthday, name, surname, patronymic;
	int id, quantity, num, num_, passport_id;
	bool gender;
	Date birthday_;
	pair<int, int> choice_;
	switch (variant)
	{
	case 1:
		printVisitor(visitor_mapper.findAll());
		break;
	case 2:
		cout << "Enter name:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, name);

		cout << "Enter surname:" << endl;
		getline(cin, surname);

		cout << "Enter patronymic:" << endl;
		getline(cin, patronymic);

		cout << "Enter birthday(year-month-day):" << endl;
		getline(cin, birthday);

		cout << "Enter gender:" << endl;
		cin >> gender;

		cout << "Enter passport id:" << endl;
		cin >> passport_id;

		visitor.setPassport_id(passport_id);
		visitor.setId(-1);
		visitor.setName(name);
		visitor.setSurname(surname);
		visitor.setPatronymic(patronymic);
		visitor.setGender(gender);
		birthday_ = visitor.setStringDate(birthday);
		visitor.setDate(birthday_);
		visitor_mapper.save(visitor);
		break;

	case 3:
		printVisitor(visitors = visitor_mapper.findAll());
		cout << "Enter visitor id" << endl;
		cin >> id;

		cout << "Enter new name:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, name);

		cout << "Enter new surname:" << endl;
		getline(cin, surname);

		cout << "Enter new patronymic:" << endl;
		getline(cin, patronymic);

		cout << "Enter new birthday:" << endl;
		getline(cin, birthday);

		cout << "Enter gender:" << endl;
		cin >> gender;

		cout << "Enter passport id:" << endl;
		cin >> passport_id;

		visitor.setId(visitors[id - 1L]->getId());
		visitor.setName(name);
		visitor.setSurname(surname);
		visitor.setPatronymic(patronymic);
		visitor.setGender(gender);
		birthday_ = visitor.setStringDate(birthday);
		visitor.setDate(birthday_);
		visitor.setPassport_id(passport_id);
		visitor_mapper.save(visitor);
		break;

	case 4:
		printVisitor(visitors = visitor_mapper.findAll());
		cout << "Enter visitor id" << endl;
		cin >> id;
		visitor_mapper.remove(visitors[id - 1L]->getId());
		break;
	case 5:
		choice_ = choice
		(visitors = visitor_mapper.findAll(),
			services = service_mapper.findAll()
		);
		num = choice_.first;
		num_ = choice_.second;
		if (!(num && num_)) break;
		cout << "Write quantity:" << endl;
		cin >> quantity;
		visitor_mapper.addService(services[num_ - 1]->getId(), visitors[num - 1]->getId(), quantity);
		break;
	case 6:
		choice_ = choice
		(visitors = visitor_mapper.findAll(),
			services = service_mapper.findAll()
		);
		num = choice_.first;
		num_ = choice_.second;
		if (!(num && num_)) break;
		cout << "Write quantity:" << endl;
		cin >> quantity;
		visitor_mapper.removeService(services[num_ - 1]->getId(), visitors[num - 1]->getId(), quantity);
		break;

	default:
		break;
	}
	return 0;
}

int serviceMenu(int variant, Service& service,
	Service_Mapper& service_mapper, vector<Service*>& services) {
	string name;
	int id;
	int price;
	switch (variant)
	{
	case 1:
		printService(service_mapper.findAll());
		break;
	case 2:
		cout << "Enter name:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, name);

		cout << "Enter price:" << endl;
		cin >> price;

		service.setId(-1);
		service.setName(name);
		service.setPrice(price);
		service_mapper.save(service);
		break;

	case 3:
		printService(services = service_mapper.findAll());
		cout << "Enter visitor id" << endl;
		cin >> id;

		cout << "Enter new name:" << endl;
		cin.ignore((numeric_limits<streamsize>::max)(), '\n');
		getline(cin, name);

		cout << "Enter new price:" << endl;
		cin >> price;

		service.setId(services[id - 1L]->getId());
		service.setName(name);
		service.setPrice(price);
		service_mapper.save(service);
		break;

	case 4:
		printService(services = service_mapper.findAll());
		cout << "Enter  id" << endl;
		cin >> id;
		service_mapper.remove(services[id - 1L]->getId());
		break;

	default:
		break;
	}
	return 0;
}

int get_variant(int count) {
	int variant;
	string s;
	getline(cin, s);
	while (sscanf(s.c_str(), "%d", &variant) != 1 || variant < 1 || variant > count) {
		cout << "Incorrect input. Try again: ";
		getline(cin, s);
	}
	return variant;
}

void menu()
{
	DataBase db = DataBase();
	Room_Mapper room_mapper(db);
	Staff_Mapper staff_mapper(db);
	Passport_Mapper passport_mapper(db);
	Registration_Mapper registration_mapper(db);
	Service_Mapper service_mapper(db);
	Visitor_Mapper visitor_mapper(db, service_mapper);


	vector<Room*> rooms;
	Room room;

	vector<Staff*> staffs;
	Staff staff;

	vector<Passport*> passports;
	Passport passport;

	vector<Registration*> registrations;
	Registration registration;

	vector<Visitor*> visitors;
	Visitor visitor;

	vector<Service*> services;
	Service service;


	pair<long, long> c;

	vector<int> nums;
	long num;
	long num2;
	double d_num;
	bool flag;
	int variant, sec_variant;

	while (true)
	{
		cout << "Hostel" << endl;
		cout << "----------------------" << endl;
		cout << "1. Room" << endl;
		cout << "2. Staff" << endl;
		cout << "3. Registration" << endl;
		cout << "4. Passport" << endl;
		cout << "5. Visitor" << endl;
		cout << "6. Service" << endl;
		cout << "0. Exit" << endl;
		cout << "\nEnter the item" << endl;

		cin >> variant;

		switch (variant)
		{
		case 1:
			//Room info
			cout << "Room" << endl;
			cout << "1. List all room" << endl;
			cout << "2. Add a room" << endl;
			cout << "3. Edit a room" << endl;
			cout << "4. Delete room" << endl;
			cout << "0. Back" << endl;
			cin >> sec_variant;
			roomMenu(sec_variant, room, room_mapper, rooms);
			cout << "Click the any button to continue";
			cin.get();
			cin.get();
			system("cls");
			break;

		case 2:
			//Staff menu
			cout << "Staff:" << endl;
			cout << "1. List all staffs" << endl;
			cout << "2. Add an staff" << endl;
			cout << "3. Edit an staff" << endl;
			cout << "4. Remove an staff" << endl;
			cout << "0. Back" << endl;
			cin >> sec_variant;
			staffMenu(sec_variant, staff, staff_mapper, staffs);
			cout << "Click the any button to continue";
			cin.get();
			cin.get();
			system("cls");
			break;

		case 3:
			//Registration menu
			cout << "Registration:" << endl;
			cout << "1. List all registration" << endl;
			cout << "2. Add an registration" << endl;
			cout << "3. Edit an registration" << endl;
			cout << "4. Remove an registration" << endl;
			cout << "0. Back" << endl;
			cin >> sec_variant;
			registrationMenu(sec_variant, registration, registration_mapper, registrations);
			cout << "Click the any button to continue";
			cin.get();
			cin.get();
			system("cls");
			break;

		case 4:
			//Passport menu
			cout << "Passport:" << endl;
			cout << "1. List all passport" << endl;
			cout << "2. Add an passport" << endl;
			cout << "3. Edit an passport" << endl;
			cout << "4. Remove an passport" << endl;
			cout << "0. Back" << endl;
			cin >> sec_variant;
			passportMenu(sec_variant, passport, passport_mapper, passports);
			cout << "Click the any button to continue";
			cin.get();
			cin.get();
			system("cls");
			break;

		case 5:
			//Visitor menu
			cout << "Visitor:" << endl;
			cout << "1. List all visitor" << endl;
			cout << "2. Add an visitor" << endl;
			cout << "3. Edit an visitor" << endl;
			cout << "4. Remove an visitor" << endl;
			cout << "5. Add service to visitor" << endl;
			cout << "6. Remove service from visitor" << endl;
			cout << "0. Back" << endl;
			cin >> sec_variant;
			visitorMenu(sec_variant, visitor, visitor_mapper, visitors, service, service_mapper, services);
			cout << "Click the any button to continue";
			cin.get();
			cin.get();
			system("cls");
			break;

		case 6:
			//Service menu
			cout << "Service:" << endl;
			cout << "1. List all service" << endl;
			cout << "2. Add an service" << endl;
			cout << "3. Edit an service" << endl;
			cout << "4. Remove an service" << endl;
			cout << "0. Back" << endl;
			cin >> sec_variant;
			serviceMenu(sec_variant, service, service_mapper, services);
			cout << "Click the any button to continue";
			cin.get();
			cin.get();
			system("cls");
			break;
		case 0:
			return;
		}
	}
}


int main()
{
	setlocale(0, "");
	menu();
	return 0;
}

