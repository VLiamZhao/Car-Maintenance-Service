package org.mynode.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    //@SequenceGenerator(name = "role_id_generator", sequenceName = "role_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = SEQUENCE, generator = "role_id_generator")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "allowed_resource")
    private String allowedResource;
    @Column(name = "allowed_read")
    private boolean allowedRead;
    @Column(name = "allowed_create")
    private boolean allowedCreate;
    @Column(name = "allowed_update")
    private boolean allowedUpdate;
    @Column(name = "allowed_delete")
    private boolean allowedDelete;

    @ManyToMany(mappedBy = "roleList")
    private List<Customer> customerList;

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

    public String getAllowedResource() {
        return allowedResource;
    }

    public void setAllowedResource(String allowedResource) {
        this.allowedResource = allowedResource;
    }

    public boolean isAllowedRead() {
        return allowedRead;
    }

    public void setAllowedRead(boolean allowedRead) {
        this.allowedRead = allowedRead;
    }

    public boolean isAllowedCreate() {
        return allowedCreate;
    }

    public void setAllowedCreate(boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }

    public boolean isAllowedUpdate() {
        return allowedUpdate;
    }

    public void setAllowedUpdate(boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }

    public boolean isAllowedDelete() {
        return allowedDelete;
    }

    public void setAllowedDelete(boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}