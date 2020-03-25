package org.mynode.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {

    public Car() {
        List<Maintenance> maintenances = new ArrayList<>();
    }

    public Car(String type) {
        this.type = type;
        List<Maintenance> maintenances = new ArrayList<>();
    }

    public Car(String type, BigDecimal price) {
        this.type = type;
        this.price = price;
        List<Maintenance> maintenances = new ArrayList<>();
    }

    public Car(String type, BigDecimal price, LocalDate reg_date) {
        this.type = type;
        this.price = price;
        this.regi_date = reg_date;
        List<Maintenance> maintenances = new ArrayList<>();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "type")
    private  String type;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "regi_date")
    private LocalDate regi_date;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer customer;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Maintenance> maintenances;

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

    public List<Maintenance> getMaintenances() {
        /* This solve the session closed exception when the fetch type is lazy */
        try {
            int size = maintenances.size();
        }
        catch (Exception e) {
            return null;
        }

        return maintenances;
    }
//    public void setMaintenances(List<Maintenance> maintenances) {
//        /* Create link between parent and children objects automatically */
//        for (Maintenance m : maintenances) {
//            if (m.getCar() == null) {m.setCar(this);}
//        }
//
//        this.maintenances = maintenances;
//    }
//
//    public void addMaintenance (Maintenance maintenance) {
//        maintenance.setCar(this);
//        this.maintenances.add(maintenance);
//    }
}
