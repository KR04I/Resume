package Mappers;

import DB_hotel.VisitorServiceEntity;

import java.util.List;

public class VisitorServiceMapper extends BaseMapper<VisitorServiceEntity>
{
    public List<VisitorServiceEntity> findAllByQuantity(Object quantity){
        return findByField(".byQuantity", quantity);
    }

    public List<VisitorServiceEntity> findAllByServiceId(Object serviceByServiceId){
        return findByField(".byServiceId", serviceByServiceId);
    }

    public List<VisitorServiceEntity> findAllByVisitorId(Object visitorByVisitorId){
        return findByField(".byVisitorId", visitorByVisitorId);
    }

    @Override
    protected Class<VisitorServiceEntity> getType() {
        return VisitorServiceEntity.class;
    }

    @Override
    protected String getTableName() {
        return "VisitorServiceEntity";
    }
}
