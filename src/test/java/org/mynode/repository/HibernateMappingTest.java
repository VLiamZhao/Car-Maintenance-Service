package org.mynode.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.mynode.util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateMappingTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void mappingCustomerClassTest() {
        Customer customer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            customer = session.get(Customer.class, 1L);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        Assert.assertNotNull(customer);
    }

    @Test
    public void mappingCarClassTest(){
        Car car = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            car = session.get(Car.class, 2L);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        Assert.assertNotNull(car);
    }


    @Test
    public void mappingTest() {
        String hql = "FROM Car";
        List<Car> cars = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Car> query = session.createQuery(hql);
            cars = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

//        accounts.forEach(account -> logger.info(account.toString()+"test2"));

        Assert.assertNotNull(cars);
    }
}