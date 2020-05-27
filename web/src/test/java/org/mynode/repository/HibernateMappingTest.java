//package org.mynode.repository;
//
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mynode.init.ApplicationBootstrap;
//import org.mynode.model.Car;
//import org.mynode.model.Customer;
//import org.mynode.model.Maintenance;
//import org.mynode.util.HibernateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ApplicationBootstrap.class)
//public class HibernateMappingTest {
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Test
//    public void mappingCustomerClassTest() {
//        Customer customer = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            customer = session.get(Customer.class, 1L);
//        }
//        catch (Exception e){
//            logger.error(e.getMessage());
//        }
//        Assert.assertNotNull(customer);
//    }
//
//    @Test
//    public void mappingCarClassTest(){
//        Car car = null;
//        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            car = session.get(Car.class, 2L);
//        }
//        catch (Exception e){
//            logger.error(e.getMessage());
//        }
//        Assert.assertNotNull(car);
//    }
//
//    @Test
//    public void mappingMaintenanceClassTest(){
//        Maintenance maintenance = null;
//        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            maintenance = session.get(Maintenance.class, 2L);
//        }
//        catch (Exception e){
//            logger.error(e.getMessage());
//        }
//        Assert.assertNotNull(maintenance);
//    }
//
//    @Test
//    public void mappingTest() {
//        String hql = "FROM Car";
//        List<Car> cars = null;
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query<Car> query = session.createQuery(hql);
//            cars = query.list();
//        }
//        catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//
////        accounts.forEach(account -> logger.info(account.toString()+"test2"));
//
//        Assert.assertNotNull(cars);
//    }
//}