package org.mynode.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoTest {
    @Autowired
    CarDao carDao;
    @Autowired
    CustomerDao customerDao;
    Logger logger = LoggerFactory.getLogger(getClass());
    Car c1, c2;
    Customer cu;

    @Before
    public void init() {
        logger.debug("Test will start...");
        c1 = new Car("Toyota Camry");
        c2 = new Car("lexus E200");
        cu = new Customer("Liam");
        cu.addCar(c1);
        cu.addCar(c2);
        cu = customerDao.save(cu);
        c1 = carDao.save(c1);
        c2 = carDao.save(c2);
        assert(c1.getId() != 0);
        assert (c2.getId() != 0);
        assert (cu.getId() != 0);
    }

    @After
    public void tearDown() {
        assert(carDao.deleteById(c1.getId()));
        assert(carDao.deleteById(c2.getId()));
        assert(customerDao.deleteById(cu.getId()));
    }

    @Test
    public void getCustomerEagerByTest(){
        logger.debug("Test of getCustomerEagerBy will start...");
        List<Car> cars = customerDao.getCustomerEagerBy(cu.getId()).getCars();
        int expectedCount = 2;
        Assert.assertEquals(expectedCount, cars.size());
    }

    @Test
    public void getCustomersTest() {
        logger.debug("Test of getCustomers will start...");
        int expectedCount = 3; //2 records was already in the table.
        List<Customer> customers = customerDao.getCustomers();
        Assert.assertEquals(expectedCount, customers.size());
    }

    @Test
    public void getCustomerByIdTest() {
        logger.debug("Test of getCustomerById will start...");
        Customer customer = customerDao.getCustomerById(cu.getId());
        String cuName = customer.getName();
        Assert.assertEquals("Liam", cuName);
    }
}
