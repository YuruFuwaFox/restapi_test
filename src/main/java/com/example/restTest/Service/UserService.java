package com.example.restTest.Service;

import com.example.restTest.Entity.User;
import com.example.restTest.Exception.NotFoundException;
import com.example.restTest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUser(Long id){
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

}
