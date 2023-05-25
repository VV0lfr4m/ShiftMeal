package com.example.demo.user.service;

import com.example.demo.user.entity.Address;
import com.example.demo.user.entity.User;

import java.util.Map;


public interface UserService {

    User findUserById(Long id);
    User findUserByFullName(String firstName, String lastName);
    User findUserByEmail(String email);
    User findUserByPhone(String phone);
    User findUserByParameters(Map<String, Object> parameters);

    void changeUserFullName(String firstName, String lastName);
    void changeUserEmail(String email);
    void changeUserPhone(String phone);
    void changeUserAddress(Address address);
    void changeUserPaymentMethod(String method);
}
