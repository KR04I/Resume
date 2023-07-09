package Mappers;

import DB_hotel.StaffEntity;

import java.util.List;

public class StaffMapper extends BaseMapper<StaffEntity>
{
    public List<StaffEntity> findAllByName(Object name){
        return findByField(".byName", name);
    }

    public List<StaffEntity> findAllBySurname(Object surname){
        return findByField(".bySurname", surname);
    }

    public List<StaffEntity> findAllByPatronymic(Object patronymic){
        return findByField(".byPatronymic", patronymic);
    }

    public List<StaffEntity> findAllByPassportId(Object passportId){
        return findByField(".byPassportId", passportId);
    }

    @Override
    protected Class<StaffEntity> getType() {
        return StaffEntity.class;
    }

    @Override
    protected String getTableName() {
        return "StaffEntity";
    }
}
