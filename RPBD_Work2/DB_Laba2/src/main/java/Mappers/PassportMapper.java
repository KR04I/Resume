package Mappers;

import DB_hotel.PassportEntity;

import java.util.List;

public class PassportMapper extends BaseMapper<PassportEntity> {

    /*
    private String address;+
    private Integer number;+
    private Date dateExtradition;+
    private String passportIssuance;+
    */

    public List<PassportEntity> findAllByAddress(Object address){
        return findByField(".byAddress", address);
    }

    public List<PassportEntity> findAllByNumber(Object number){
        return findByField(".byNumber", number);
    }

    public List<PassportEntity> findAllByDateExtradition(Object dateExtradition){
        return findByField(".byDateExtradition", dateExtradition);
    }

    public List<PassportEntity> findAllByPassportIssuance(Object passportIssuance){
        return findByField(".byPassportIssuance", passportIssuance);
    }

    @Override
    protected Class<PassportEntity> getType() {
        return PassportEntity.class;
    }

    @Override
    protected String getTableName() {
        return "PassportEntity";
    }
}
