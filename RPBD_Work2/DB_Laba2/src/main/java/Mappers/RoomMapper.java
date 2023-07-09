package Mappers;

import DB_hotel.RoomEntity;

import java.util.List;

public class RoomMapper extends BaseMapper<RoomEntity>
{
    public List<RoomEntity> findAllByRoomNumber(Object roomNumber){
        return findByField(".byRoomNumber", roomNumber);
    }

    public List<RoomEntity> findAllByLevel(Object level){
        return findByField(".byLevel", level);
    }

    public List<RoomEntity> findAllByNumberAvailableSeats(Object numberAvailableSeats){
        return findByField(".byNumberAvailableSeats", numberAvailableSeats);
    }

    public List<RoomEntity> findAllByLivingPeople(Object livingPeople){
        return findByField(".byLivingPeople", livingPeople);
    }

    public List<RoomEntity> findAllByOrder(Object order) {
        //return ((Order)order).getOrderDatesById();
        return null;
    }

//    public List<Clients> findAllByOrderDate(String orderDate) {
//        String hql = String.format("Select C From %s C join C.OrderDate OD where OD.orderDate like :orderDate", getTableName());
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        Query<Clients> query = session.createQuery(hql, getType());
//        query.setParameter("orderDate", "%" + orderDate + "%");
//        List<Clients> list = query.list();
//        session.close();
//        return list;
//    }

    @Override
    protected Class<RoomEntity> getType() {
        return RoomEntity.class;
    }

    @Override
    protected String getTableName() {
        return "RoomEntity";
    }
}
