package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;

import org.junit.Assert;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;




public class UserRepositoryTest extends StudyApplicationTests {

    //Dependency Injection(DI)

    @Autowired
    private UserRepository userRepository;

    @org.junit.jupiter.api.Test
    public void create() {
        //String sql = insert into user(%s, %s, %d) value(account, email,age);
        User user = new User();

        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser4");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);
    }

    @org.junit.jupiter.api.Test
    public void read() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            System.out.println("user : " + selectUser);
            System.out.println("email : " + selectUser.getEmail());
        });


    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void update() {

        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPt");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });


    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        Assert.assertTrue(user.isPresent()); //true

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);


        });

        Optional<User> deleteUser = userRepository.findById(1L);

        Assert.assertFalse(deleteUser.isPresent()); //false

    }
}
