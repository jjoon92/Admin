package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;

import com.example.study.model.enumclass.UserStatus;
import org.junit.Assert;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;




public class UserRepositoryTest extends StudyApplicationTests {

    //Dependency Injection(DI)

    @Autowired
    private UserRepository userRepository;

    @org.junit.jupiter.api.Test
    public void create() {
        String account = "Test03";
        String password="Test03";
        UserStatus status=UserStatus.REGISTERED;
        String email="Test01@gmail.com";
        String phoneNumber="010-1111-3333";
        LocalDateTime registeredAt=LocalDateTime.now();
        LocalDateTime createdAt=LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();


        User newUser = userRepository.save(user);
        Assert.assertNotNull(newUser);

    }

    @org.junit.jupiter.api.Test
    @Transactional
    public void read() {

        User user= userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        /*user
                .setEmail("")
                .setPhoneNumber("")
                .setStatus("");

        User u = new User().setAccount("").setEmail("").setPassword("");*/


        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("------------------????????????----------------");
                System.out.println("????????? : " + orderGroup.getRevName());
                System.out.println("????????? : " + orderGroup.getRevAddress());
                System.out.println("????????? : " + orderGroup.getTotalPrice());
                System.out.println("????????? : " + orderGroup.getTotalQuantity());
                System.out.println("------------------????????????----------------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("???????????? ??????: "+orderDetail.getItem().getPartner().getName());
                    System.out.println("???????????? ????????????: "+orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("?????? ??????: "+orderDetail.getItem().getName());
                    System.out.println("???????????? ?????? : "+orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("????????? ??????: "+orderDetail.getStatus());
                    System.out.println("??????????????????: "+orderDetail.getArrivalDate());



                });

            });

        }

        Assert.assertNotNull(user);



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
