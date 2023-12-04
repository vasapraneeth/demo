package com.user.demo.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_addresses")
public class UserAddresses {

    @Id
    @Column(name = "full_address")
    private String fullAddress;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private UserMaster userMaster;

    public UserAddresses() {}

    public UserAddresses(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public UserMaster getUserMaster() {
        return userMaster;
    }

    public void setUserMaster(UserMaster userMaster) {
        this.userMaster = userMaster;
    }

    @Override
    public String toString() {
        return "UserAddresses{" +
                "fullAddress='" + fullAddress + '\'' +
                '}';
    }
}
