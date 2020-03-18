package org.mynode.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    public Customer() {
        cars = new ArrayList<>();
    }

    public Customer(String name) {
        this.name = name;
        cars = new ArrayList<>();
    }

    public Customer(String name, String description) {
        this.name = name;
        this.description = description;
        cars = new ArrayList<>();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "customer")
    private List<Car> cars;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Car> getCars() {
        /* This solve the session closed exception when the fetch type is lazy */
        try {
            int size = cars.size();
        }
        catch (Exception e) {
            return null;
        }

        return cars;
    }
    public void setCars(List<Car> cars) {
        /* Create link between parent and children objects automatically */
        for (Car c : cars) {
            if (c.getCustomer() == null) {c.setCustomer(this);}
        }

        this.cars = cars;
    }

    public void addCar(Car car) {
        car.setCustomer(this);
        this.cars.add(car);
    }


}
