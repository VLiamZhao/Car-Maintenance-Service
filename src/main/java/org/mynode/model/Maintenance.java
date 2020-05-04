package org.mynode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.mynode.model.view.JsView;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
public class Maintenance {
    public Maintenance(long id, String component, BigDecimal cost, LocalDate date, String description) {
        this.component = component;
        this.cost = cost;
        this.date = date;
        this.description = description;
    }

    public Maintenance(String component) {
        this.component = component;
    }
    public Maintenance() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({JsView.User.class})
    private long id;

    @Column(name = "component")
    @JsonView({JsView.User.class})
    private String component;

    @Column(name = "cost")
    @JsonView({JsView.User.class})
    private BigDecimal cost;

    @Column(name = "date")
    //TODO change formate to MM/dd/YYYY
    @JsonView({JsView.User.class})
    private LocalDate date;

    @Column(name = "description")
    @JsonView({JsView.User.class})
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonView({JsView.User.class})
    private Car car;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
