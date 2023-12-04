package com.user.demo.demo.dao;

import com.user.demo.demo.entity.UserAddresses;
import com.user.demo.demo.entity.UserMaster;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    // constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<UserMaster> findAllUsers() {
        TypedQuery<UserMaster> query = entityManager.createQuery("SELECT e FROM UserMaster e", UserMaster.class);
        List<UserMaster> theUsers = query.getResultList();
        return theUsers;
    }

    @Override
    public UserMaster findUserMasterById(int theId) {
        return entityManager.find(UserMaster.class, theId);
    }

    @Override
    @Transactional
    public void update(UserMaster tempUserMaster) {
        entityManager.merge(tempUserMaster);
    }

    @Override
    @Transactional
    public void deleteUserMasterById(int theId) {
        UserMaster tempUserMaster =entityManager.find(UserMaster.class, theId);

        //get the addresses
        List<UserAddresses> userAddresses = tempUserMaster.getUserAddresses();

        // break association of all userAddresses for the userMaster
        for(UserAddresses tempUserAddress: userAddresses) {
            tempUserAddress.setUserMaster(null);
        }

        // delete the userMaster
        entityManager.remove(tempUserMaster);
    }

    @Override
    public List<UserAddresses> findUserAddressesByUserId(int theId) {

        // create query
        TypedQuery<UserAddresses> query = entityManager.createQuery("from UserAddresses where userMaster.id = :data", UserAddresses.class);
        query.setParameter("data", theId);

        // execute query
        List<UserAddresses> userAddresses =query.getResultList();
        return userAddresses;
    }

}
