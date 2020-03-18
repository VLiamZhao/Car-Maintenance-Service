package org.mynode.repository;

import org.mynode.model.Car;
import org.mynode.model.Customer;

import java.util.List;

public interface CarDao {
    Car save(Car car);
    Car getCarById(long id);
    List<Car> getCarsByCustomer(Customer customer);
    boolean deleteById(long id);
}
