package org.mynode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.codec.digest.DigestUtils;
import org.mynode.model.view.JsView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    public Customer() {
        cars = new ArrayList<>();
        roleList = new ArrayList<>();
    }

    public Customer(String name) {
        this.name = name;
        cars = new ArrayList<>();
        roleList = new ArrayList<>();
    }

    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = DigestUtils.md5Hex(password.trim());
        cars = new ArrayList<>();
        roleList = new ArrayList<>();
    }

    public Customer(String name, String description) {
        this.name = name;
        this.description = description;
        cars = new ArrayList<>();
        roleList = new ArrayList<>();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({JsView.User.class})
    private long id;

    @Column(name = "name")
    @JsonView({JsView.User.class})
    private String name;

    @Column(name = "description")
    @JsonView({JsView.User.class})
    private String description;

    @Column(name = "email")
    @JsonView({JsView.User.class})
    private String email;

    @Column(name = "password")
    @JsonView({JsView.Admin.class})
    private String password;

    @Column(name = "secret_key")
    private String secretKey;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonView({JsView.User.class})
    private List<Car> cars;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_role",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    @JsonIgnore
    private List<Role> roleList;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password.trim());
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

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


//    public void setCars(List<Car> cars) {
//        /* Create link between parent and children objects automatically */
//        for (Car c : cars) {
//            if (c.getCustomer() == null) {c.setCustomer(this);}
//        }
//
//        this.cars = cars;
//    }
//
//    public void addCar(Car car) {
//        car.setCustomer(this);
//        this.cars.add(car);
//    }


    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
