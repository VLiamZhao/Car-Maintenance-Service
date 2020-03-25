package org.mynode.service;

import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.mynode.repository.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarService {
    @Autowired
    private CarDao carDao;

    public Car save(Car car){
        return carDao.save(car);
    }

    public Car getCarById(long id){
        return carDao.getCarById(id);
    }

    public List<Car> getCarsByCustomer(Customer customer){
        return carDao.getCarsByCustomer(customer);
    }

    public boolean deleteCarById(long id){
        return carDao.deleteById(id);
    }

    public List<Car> getCars(){
        return carDao.getCars();
    }
}
