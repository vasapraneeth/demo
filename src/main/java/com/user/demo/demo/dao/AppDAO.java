package com.user.demo.demo.dao;

import com.user.demo.demo.entity.UserAddresses;
import com.user.demo.demo.entity.UserMaster;

import java.util.List;

public interface AppDAO {

    public List<UserMaster> findAllUsers();

    UserMaster findUserMasterById(int theId);

    void update(UserMaster tempUserMaster);

    void deleteUserMasterById(int theId);

    List<UserAddresses> findUserAddressesByUserId(int theId);

}
