package com.example.demo.user.service;

import com.example.demo.user.entity.Address;

import java.util.List;

public interface AddressService {
    Address inquireDeliveryAddress();
    void changeDeliveryAddress(Address addressNew, boolean chooseCurrent);
    List<Address> searchAddresses(String query);
}
