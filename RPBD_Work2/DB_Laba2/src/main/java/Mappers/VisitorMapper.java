package Mappers;

import DB_hotel.PassportEntity;
import DB_hotel.VisitorEntity;

import java.util.List;

public class VisitorMapper extends BaseMapper<VisitorEntity>
{

 //   public List<VisitorEntity> findByPassport(Object passport){
 //       return ((PassportEntity)passport).getVisitorsById();
 //   }
    public List<VisitorEntity> findAllByPassportId(Object passportId){
        return findByField(".byPassportId", passportId);
    }

    public List<VisitorEntity> findAllByName(Object name){
        return findByField(".byName", name);
    }

    public List<VisitorEntity> findAllBySurname(Object surname){
        return findByField(".bySurname", surname);
    }

    public List<VisitorEntity> findAllByPatronymic(Object patronymic){
        return findByField(".byPatronymic", patronymic);
    }

    public List<VisitorEntity> findAllByBirthday(Object birthday){
        return findByField(".byBirthday", birthday);
    }

    public List<VisitorEntity> findAlByGender(Object gender){
        return findByField(".byGender", gender);
    }

    @Override
    protected Class<VisitorEntity> getType() {
        return VisitorEntity.class;
    }

    @Override
    protected String getTableName() {
        return "VisitorEntity";
    }
}
