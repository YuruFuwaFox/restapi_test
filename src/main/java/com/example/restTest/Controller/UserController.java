package com.example.restTest.Controller;

import com.example.restTest.Entity.User;
import com.example.restTest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUser(){
        return userService.findUsers();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        return userService.findUser(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@Validated @RequestBody User user){
        return userService.save(user);
    }


}
