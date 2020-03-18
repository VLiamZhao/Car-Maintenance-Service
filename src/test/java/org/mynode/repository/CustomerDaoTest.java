package org.mynode.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoTest {
    CarDao carDao = new CarDaoImpl();
    CustomerDao customerDao = new CustomerDaoImpl();
    Logger logger = LoggerFactory.getLogger(getClass());
    Car c1, c2;
    Customer cu;

    @Before
    public void init() {
//        c1 = new Car("Toyota Camry");
//        c2 = new Car("lexus E350");
        cu = new Customer("Tony");
        customerDao.save(cu);
//        cu.addCar(c1);
//        cu.addCar(c2);
//        assert(0 != customerDao.save(cu).getId());
//        assert(0 != carDao.save(c1).getId());
//        assert(0 != carDao.save(c2).getId());
    }

    @After
    public void tearDown() {
//        assert(carDao.deleteById(c1.getId()));
//        assert(carDao.deleteById(c2.getId()));
//        assert(customerDao.deleteById(cu.getId()));
    }

    @Test
    public void getCustomerTest(){}
}
