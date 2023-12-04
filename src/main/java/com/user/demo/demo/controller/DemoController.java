package com.user.demo.demo.controller;

import com.user.demo.demo.dao.AppDAO;
import com.user.demo.demo.entity.UserAddresses;
import com.user.demo.demo.entity.UserMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class DemoController {
    private AppDAO appDAO;

    @Autowired
    public DemoController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @GetMapping("/hello")
    public String sayHello(Model theModel) {
        theModel.addAttribute("theDate", new Date());
        return "helloworld";
    }

    @GetMapping("/users")
    public String listUsers(Model theModel) {
        List<UserMaster> theUsers = appDAO.findAllUsers();

        theModel.addAttribute("users", theUsers);

        return "users/users-list";
    }

    @GetMapping("/showFormForAddingUsers")
    public String showFormForAddingUsers(Model theModel) {
        UserMaster theUser = new UserMaster();

        theModel.addAttribute("user", theUser);

        return "users/user-form";
    }

    @GetMapping("/showFormForUpdatingUsers")
    public String showFormForUpdatingUsers(@RequestParam("id") int theId, Model theModel) {
        UserMaster theUser = appDAO.findUserMasterById(theId);

        theModel.addAttribute("user", theUser);

        return "users/user-form";

    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserMaster theUser) {
         appDAO.update(theUser);

        return "redirect:/api/users";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int theId) {
        appDAO.deleteUserMasterById(theId);

        return "redirect:/api/users";
    }


    // Addresses
    @GetMapping("/userAddress")
    public String viewUserAddresses(@RequestParam("id") int theId, Model theModel) {
        List<UserAddresses> theAddresses = appDAO.findUserAddressesByUserId(theId);

        theModel.addAttribute("addresses", theAddresses);

        return "addresses/addresses-list";
    }

}
