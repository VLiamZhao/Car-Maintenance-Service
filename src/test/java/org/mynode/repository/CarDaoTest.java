package org.mynode.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CarDaoTest {
  CarDao carDao = new CarDaoImpl();
  CustomerDao customerDao = new CustomerDaoImpl();
  Logger logger = LoggerFactory.getLogger(getClass());
  Car c1, c2;
  Customer cu;

  @Before
  public void init() {
    c1 = new Car("Toyota Camry");
    c2 = new Car("lexus E200");
    cu = new Customer("Liam");
    cu.addCar(c1);
    cu.addCar(c2);
    cu = customerDao.save(cu);
    c1 = carDao.save(c1);
    c2 = carDao.save(c2);
    assert (c1.getId() != 0);
    assert (c2.getId() != 0);
    assert (cu.getId() != 0);
  }

    @After
    public void tearDown() {
        assert(carDao.deleteById(c1.getId()));
        assert(carDao.deleteById(c2.getId()));
        assert(customerDao.deleteById(cu.getId()));
    }

//    @Test
//    public void getCarsByCustomerTest() {
//      List<Car> cars = carDao.getCarsByCustomer(cu);
//      Assert.assertTrue(cars.size() == 2);
//    }

    @Test
    public void getCarByIdTest() {
      Car car = carDao.getCarById(c1.getId());
      Assert.assertTrue(car.getType().equals("Toyota Camry"));
    }

    @Test
    public void getCarsTest() {
      List<Car> cars = carDao.getCars();
      int expectedCount = 3;
      Assert.assertEquals(expectedCount, cars.size());
    }
}