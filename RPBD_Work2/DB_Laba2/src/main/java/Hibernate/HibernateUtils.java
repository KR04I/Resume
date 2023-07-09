package Hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class HibernateUtils {
    private static HibernateUtils instance;
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static EntityTransaction transaction;

    private HibernateUtils() {}

    private static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        HibernateUtils.entityManagerFactory = entityManagerFactory;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null)
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    public EntityTransaction getTransaction() {
        if (transaction == null || !transaction.isActive())
            transaction = entityManager.getTransaction();
        return transaction;
    }

    public static HibernateUtils getInstance(){
        if (instance == null){
            instance = new HibernateUtils();
            instance.setEntityManagerFactory(Persistence.createEntityManagerFactory("default"));
        }
        return  instance;
    }
}