#include "Storage.h"

Storage* Storage::storage;

Storage::Storage()
{
}

Storage* Storage::getInstance()
{
	if (storage == nullptr)
	{
		storage = new Storage();
	}
	return storage;
}

Room* Storage::getRoom(int id)
{
	auto it = this->room.find(id);
	if (it != room.end())
		return &(this->room.find(id)->second);
	return nullptr;
}

Registration* Storage::getRegistration(int id)
{
	auto it = this->registration.find(id);
	if (it != registration.end())
		return &(this->registration.find(id)->second);
	return nullptr;
}

Visitor* Storage::getVisitor(int id)
{
	auto it = this->visitor.find(id);
	if (it != visitor.end())
		return &(this->visitor.find(id)->second);
	return nullptr;
}

Passport* Storage::getPassport(int id)
{
	auto it = this->passport.find(id);
	if (it != passport.end())
		return &(this->passport.find(id)->second);
	return nullptr;
}

Staff* Storage::getStaff(int id)
{
	auto it = this->staff.find(id);
	if (it != staff.end())
		return &(this->staff.find(id)->second);
	return nullptr;
}

Service* Storage::getService(int id)
{
	auto it = this->service.find(id);
	if (it != service.end())
		return &(this->service.find(id)->second);
	return nullptr;
}

void Storage::putRoom(Room& room)
{
	auto it = this->room.find(room.getId());
	if (it == this->room.end())
	{
		this->room[room.getId()] = room;
		return;
	}
	auto& r = it->second;
	r.setLevel(room.getLevel());
	r.setLiving_people(room.getLiving_people());
	r.setNumber_available_seats(room.getNumber_available_seats());
	r.setRoom_number(room.getRoom_number());
}

void Storage::putPassport(Passport& passport_info)
{
	auto it = this->passport.find(passport_info.getId());
	if (it == this->passport.end()) {
		this->passport[passport_info.getId()] = passport_info;
		return;
	}
	auto& pa = it->second;
	pa.setAddress(passport_info.getAddress());
	pa.setNumber(passport_info.getNumber());
	pa.setDate(passport_info.getDate());
	pa.setPassport_issuance(passport_info.getPassport_issuance());
}

void Storage::putStaff(Staff& staff)
{
	auto it = this->staff.find(staff.getId());
	if (it == this->staff.end()) {
		this->staff[staff.getId()] = staff;
		return;
	}
	auto& st = it->second;
	st.setPassport_id(staff.getPassport_id());
	st.setName(staff.getName());
	st.setSurname(staff.getSurname());
	st.setPatronymic(staff.getPatronymic());
}

void Storage::putRegistration(Registration& registration)
{
	auto it = this->registration.find(registration.getId());
	if (it == this->registration.end()) {
		this->registration[registration.getId()] = registration;
		return;
	}
	auto& re = it->second;
	re.setVisitor_id(registration.getVisitor_id());
	re.setRoom_number(registration.getRoom_number());
	re.setParking_number(registration.getParking_number());
	re.setCar_registration_number(registration.getCar_registration_number());
	re.setDateOfEntry(registration.getDateOfEntry());
	re.setDateOfDeparture(registration.getDateOfDeparture());
}

void Storage::putVisitor(Visitor& visitor)
{
	auto it = this->visitor.find(visitor.getId());
	if (it == this->visitor.end()) {
		this->visitor[visitor.getId()] = visitor;
		return;
	}
	auto& vi = it->second;
	vi.setPassport_id(visitor.getPassport_id());
	vi.setName(visitor.getName());
	vi.setSurname(visitor.getSurname());
	vi.setPatronymic(visitor.getPatronymic());
	vi.setDate(visitor.getDate());
}

void Storage::putService(Service& service)
{
	auto it = this->service.find(service.getId());
	if (it == this->service.end()) {
		this->service[service.getId()] = service;
		return;
	}
	auto& se = it->second;
	se.setName(service.getName());
	se.setPrice(service.getPrice());
}
