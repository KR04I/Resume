package Mappers;

import DB_hotel.ServiceEntity;

import java.util.List;

public class ServiceMapper extends BaseMapper<ServiceEntity>
{

    public List<ServiceEntity> findAllByName(Object name){
        return findByField(".byName", name);
    }

    public List<ServiceEntity> findAllByPrice(Object price){
        return findByField(".byPrice", price);
    }

    @Override
    protected Class<ServiceEntity> getType() {
        return ServiceEntity.class;
    }

    @Override
    protected String getTableName() {
        return "ServiceEntity";
    }
}
