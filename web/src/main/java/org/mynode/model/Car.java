package org.mynode.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.mynode.model.view.JsView;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {
    public Car(){}
    public Car(String type) {
        this.type = type;
    }

    public Car(String type, BigDecimal price) {
        this.type = type;
        this.price = price;
    }

    public Car(String type, BigDecimal price, LocalDate regi_date) {
        this.type = type;
        this.price = price;
        this.regi_date = regi_date;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({JsView.User.class})
    private long id;

    @Column(name = "type")
    @JsonView({JsView.User.class})
    private  String type;

    @Column(name = "price")
    @JsonView({JsView.User.class})
    private BigDecimal price;

    @Column(name = "regi_date")
    @JsonView({JsView.User.class})
    private LocalDate regi_date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer customer;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    @JsonView({JsView.User.class})
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Maintenance> maintenances = new ArrayList<>();

    public LocalDate getRegDate() {
        return regi_date;
    }

    public void setRegDate(LocalDate regi_date) {
        this.regi_date = regi_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //TODO test to after removing - done
    public List<Maintenance> getMaintenances() {
        /* This solve the session closed exception when the fetch type is lazy */
//        try {
//            int size = maintenances.size();
//        }
//        catch (Exception e) {
//            return null;
//        }
        return maintenances;
    }
    public void setMaintenances(List<Maintenance> maintenances) {
        /* Create link between parent and children objects automatically */
//        for (Maintenance m : maintenances) {
//            if (m.getCar() == null) {m.setCar(this);}
//        }

        this.maintenances = maintenances;
    }

//    public void addMaintenance (Maintenance maintenance) {
//        maintenance.setCar(this);
//        this.maintenances.add(maintenance);
//    }
}
