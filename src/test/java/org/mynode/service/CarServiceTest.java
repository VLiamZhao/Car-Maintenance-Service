package org.mynode.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mynode.init.ApplicationBootstrap;
import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.mynode.model.Maintenance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class CarServiceTest {
    @Autowired
    CarService carService;
    @Autowired
    CustomerService customerService;
    @Autowired
    MaintenanceService maintenanceService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    Car c1, c2;
    Customer cu;
    Maintenance m1, m2;

    @Before
    public void init() {
        logger.debug("Test will start...");
        c1 = new Car("Toyota Corolla");
        c2 = new Car("lexus E200");
        cu = new Customer("Stephanie");
        m1 = new Maintenance("left front-door");
        m2 = new Maintenance("gear");
        c1.setCustomer(cu);
        c2.setCustomer(cu);
        m1.setCar(c1);
        m2.setCar(c2);
        cu = customerService.save(cu);
        c1 = carService.save(c1);
        c2 = carService.save(c2);
        m1 = maintenanceService.save(m1);
        m2 = maintenanceService.save(m2);
        assert (c1.getId() != 0);
        assert (c2.getId() != 0);
        assert (cu.getId() != 0);
        assert (m1.getId() != 0);
        assert (m2.getId() != 0);
    }

    @After
    public void tearDown() {
        logger.debug("Test will be finished...");
        assert(maintenanceService.deleteMaintenanceById(m1.getId()));
        assert(maintenanceService.deleteMaintenanceById(m2.getId()));
        assert(carService.deleteCarById(c1.getId()));
        assert(carService.deleteCarById(c2.getId()));
        assert(customerService.deleteCustomerById(cu.getId()));
    }

    @Test
    public void getCarsByCustomerTest() {
        List<Car> cars = carService.getCarsByCustomer(cu);
        Assert.assertTrue(cars.size() == 2);
    }

    @Test
    public void getCarByIdTest() {
        Car car = carService.getCarById(c1.getId());
        Assert.assertTrue(car.getType().equals("Toyota Corolla"));
    }

    @Test
    public void getCarsTest() {
        List<Car> cars = carService.getCars();
        int expectedCount = 4;
        Assert.assertEquals(expectedCount, cars.size());
    }
}