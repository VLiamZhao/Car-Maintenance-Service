package org.mynode.controller;

import org.mynode.model.Car;
import org.mynode.model.Customer;
import org.mynode.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/car"})
public class CarController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    CarService carService;

    /**
     * GET {prefix}/car
     * @param
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Car> getCars(){
        return carService.getCars();
    }

    /**
     * POST {prefix}/car/body
     * @param
     * @return
     */
    @RequestMapping(value = "/body", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Car createCar(@RequestBody Car car){
        Car c1 = carService.save(car);
        return c1;
    }

    /**
     * POST {prefix}/car/{id}
     * @param inputId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Car getCarById(@PathVariable (name = "id") long inputId){
        return carService.getCarById(inputId);
    }

    /**
     * POST {prefix}/car
     * @param
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public List<Car> createCarsByCustomer(@RequestBody Customer customer){
        List<Car> cars = carService.getCarsByCustomer(customer);
        return cars;
    }

    /**
     * DELETE {prefix}/car/{id}
     * @param inputId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean deleteCarById(@PathVariable (name = "id") long inputId){
        return carService.deleteCarById(inputId);
    }
}
