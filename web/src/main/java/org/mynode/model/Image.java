package org.mynode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.mynode.model.view.JsView;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "image")
public class Image {
    public Image() {}

    public Image(String fileName) {
        this.fileName = fileName;
    }

    public Image(String fileName, String s3key, LocalDateTime createTime, Customer customer) {
        this.fileName = fileName;
        this.s3key = s3key;
        this.createTime = createTime;
        this.customer = customer;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({JsView.User.class})
    private long id;

    @Column(name = "file_name")
    @JsonView({JsView.User.class})
    private String fileName;

    @Column(name = "s3key")
    @JsonView({JsView.User.class})
    private String s3key;

    @Column(name = "create_date")
    @JsonView({JsView.User.class})
    private LocalDateTime createTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getS3key() {
        return s3key;
    }

    public void setS3key(String s3key) {
        this.s3key = s3key;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
