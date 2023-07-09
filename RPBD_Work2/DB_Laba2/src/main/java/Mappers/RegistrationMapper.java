package Mappers;

import DB_hotel.RegistrationEntity;

import java.util.List;

public class RegistrationMapper extends BaseMapper<RegistrationEntity> {


    public List<RegistrationEntity> findAllByVisitorId(Object visitorId){
        return findByField(".byVisitorId", visitorId);
    }

    public List<RegistrationEntity> findAllByRoomNumber(Object roomNumber){
        return findByField(".byRoomNumber", roomNumber);
    }

    public List<RegistrationEntity> findAllByParkingNumber(Object parkingNumber){
        return findByField(".byParkingNumber", parkingNumber);
    }

    public List<RegistrationEntity> findAllByCarRegistrationNumber(Object carRegistrationNumber){
        return findByField(".byCarRegistrationNumber", carRegistrationNumber);
    }

    public List<RegistrationEntity> findAllByDateOfEntry(Object dateOfEntry){
        return findByField(".byDateOfEntry", dateOfEntry);
    }

    public List<RegistrationEntity> findAlByDateOfDeparture(Object dateOfDeparture){
        return findByField(".byDateOfDeparture", dateOfDeparture);
    }

    @Override
    protected Class<RegistrationEntity> getType() {
        return RegistrationEntity.class;
    }

    @Override
    protected String getTableName() {
        return "RegistrationEntity";
    }
}
