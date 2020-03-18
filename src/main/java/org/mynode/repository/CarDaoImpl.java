package org.mynode.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.mynode.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CarDaoImpl implements CarDao {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Car save(Car car) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
            return car;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Car getCarById(long id) {
        return null;
    }

    @Override
    public List<Car> getCarsByCustomer(Customer customer) {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        String hql = "DELETE Car where id = :targetId";
        int deletedCount = 0;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("targetId", id);
            deletedCount = query.executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The account which id is %s was deleted" + id));
        return deletedCount == 1;
    }
}
