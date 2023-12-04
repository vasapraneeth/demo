package com.user.demo.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_master")
public class UserMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;


    @Column(name = "date_of_registration")
    private String dateOfRegistration;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "userMaster", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<UserAddresses> userAddresses;

    public UserMaster() {}

    public UserMaster(String name, String password, String phoneNumber, String dateOfRegistration, String status) {
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dateOfRegistration = dateOfRegistration;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserMaster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                ", date_of_registration=" + dateOfRegistration +
                ", status='" + status + '\'' +
                '}';
    }

    public List<UserAddresses> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddresses> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public void add(UserAddresses tempUserAddress) {
        if(userAddresses == null) {
            userAddresses = new ArrayList<>();
        }
        userAddresses.add(tempUserAddress);
        tempUserAddress.setUserMaster(this);
    }
}
