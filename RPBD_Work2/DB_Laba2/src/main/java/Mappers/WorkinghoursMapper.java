package Mappers;

import DB_hotel.WorkinghoursEntity;

import java.util.List;

public class WorkinghoursMapper extends BaseMapper<WorkinghoursEntity>
{
    public List<WorkinghoursEntity> findAllByPassportId(Object passportId){
        return findByField(".byPassportId", passportId);
    }

    public List<WorkinghoursEntity> findAllByDateStart(Object datestart){
        return findByField(".byDateStart", datestart);
    }

    public List<WorkinghoursEntity> findAllByDateEnd(Object dateend){
        return findByField(".byDateEnd", dateend);
    }

    public List<WorkinghoursEntity> findAllByAddress(Object address){
        return findByField(".byAddress", address);
    }


    @Override
    protected Class<WorkinghoursEntity> getType() {
        return WorkinghoursEntity.class;
    }

    @Override
    protected String getTableName() {
        return "WorkinghoursEntity";
    }
}
