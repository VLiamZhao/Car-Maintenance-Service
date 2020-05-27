//package org.mynode.repository;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mynode.init.ApplicationBootstrap;
//import org.mynode.model.Car;
//import org.mynode.model.Customer;
//import org.mynode.model.Maintenance;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ApplicationBootstrap.class)
//public class MaintenanceDaoTest {
//    @Autowired
//    CarDao carDao;
//    @Autowired
//    CustomerDao customerDao;
//    @Autowired
//    MaintenanceDao maintenanceDao;
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    Car c1, c2;
//    Customer cu;
//    Maintenance m1, m2;
//
//    @Before
//    public void init() {
//        logger.debug("Test will start...");
//        c1 = new Car("Toyota Corolla");
//        c2 = new Car("lexus E200");
//        cu = new Customer("Stephanie");
//        m1 = new Maintenance("left front-door");
//        m2 = new Maintenance("gear");
//        c1.setCustomer(cu);
//        c2.setCustomer(cu);
//        m1.setCar(c1);
//        m2.setCar(c2);
//        cu = customerDao.save(cu);
//        c1 = carDao.save(c1);
//        c2 = carDao.save(c2);
//        m1 = maintenanceDao.save(m1);
//        m2 = maintenanceDao.save(m2);
//        assert (c1.getId() != 0);
//        assert (c2.getId() != 0);
//        assert (cu.getId() != 0);
//        assert (m1.getId() != 0);
//        assert (m2.getId() != 0);
//    }
//
//    @After
//    public void tearDown() {
//        logger.debug("Test will be finished...");
//        assert(maintenanceDao.deleteMaintenanceById(m1.getId()));
//        assert(maintenanceDao.deleteMaintenanceById(m2.getId()));
//        assert(carDao.deleteById(c1.getId()));
//        assert(carDao.deleteById(c2.getId()));
//        assert(customerDao.deleteById(cu.getId()));
//    }
//
//    @Test
//    public void getMaintenanceOrdersTest() {
//        logger.debug("Test of getMaintenanceOrders will start...");
//        int expectedCount = 4; //2 records was already in the table.
//        List<Maintenance> maintenanceOrders = maintenanceDao.getMaintenanceOrders();
//        Assert.assertEquals(expectedCount, maintenanceOrders.size());
//    }
//
//    @Test
//    public void getMaintenanceByIdTest() {
//        logger.debug("Test of getMainyId will start...");
//        Maintenance maintenance = maintenanceDao.getMaintenanceById(m1.getId());
//        String m1Component = m1.getComponent();
//        Assert.assertEquals("left front-door", m1Component);
//    }
//}