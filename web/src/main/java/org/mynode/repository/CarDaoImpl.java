package org.mynode.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.mynode.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CarDaoImpl implements CarDao {
    private Logger logger = LoggerFactory.getLogger(getClass());
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
        String hql = "FROM Car as ca where ca.id = :targetId";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Car> query = session.createQuery(hql);
            query.setParameter("targetId", id);
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Car> getCarsByCustomer(Customer customer) {
        try {
            return customer.getCars();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
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
        logger.debug(String.format("The account which id is %s was deleted",id));
        return deletedCount == 1;
    }

    @Override
    public List<Car> getCars() {
        String hql = "From Car";
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            return (List<Car>) query.list();
        }
        catch (Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }
}
