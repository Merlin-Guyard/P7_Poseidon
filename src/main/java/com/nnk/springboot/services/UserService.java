package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(int id){
        Optional<User> optionalUser= userRepository.findById(id);
        if (optionalUser.isEmpty()){
            Logger.info("No bidlist found by id",id);
            throw new RuntimeException();
        }
        return optionalUser.get();
    }

    public User getByUsername(String username){
        Optional<User> optionalUser= userRepository.findByUsername(username);
        if (optionalUser.isEmpty()){
            Logger.info("No bidlist found by username",username);
            throw new RuntimeException();
        }
        return optionalUser.get();
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateById(Integer id, User user) {
        userRepository.deleteById(id);
        userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}