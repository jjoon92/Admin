package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
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

    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void read() {




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
