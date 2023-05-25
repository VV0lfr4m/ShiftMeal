package com.example.demo.user.service;

import com.example.demo.Utils;
import com.example.demo.user.entity.Address;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.AddressRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private static final Address MOCK_ADDRESS = new Address(1l, "Ovalna street 23", true, true, "Call me");
    private final UserService userService;
    private final AddressRepo addressRepo;
    private final Utils utils;

    public AddressServiceImpl(UserService userService, AddressRepo addressRepo, Utils utils) {
        this.userService = userService;
        this.addressRepo = addressRepo;
        this.utils = utils;
    }

    @Override
    @Transactional(readOnly = true)
    public Address inquireDeliveryAddress() {
        //mock
        /*Long userId = utils.inquireLoggedInUserId();
        User user = userService.findUserById(userId);
        List<Address> addressList = user.getAddresses();
        Address deliveryAddress = addressList.stream().filter(Address::isLast).findFirst().orElse(null);*/

        Address deliveryAddress = MOCK_ADDRESS;

        return deliveryAddress;
    }

    @Override
    @Transactional
    public void changeDeliveryAddress(Address addressNew, boolean chooseCurrent) {
        User user = userService.findUserById(utils.inquireLoggedInUserId());
        List<Address> addressList = user.getAddresses();
        if (chooseCurrent) {
            //todo call gps logic
            Address currentLocation = new Address();
            addUserAddress(addressList, currentLocation);

            return;
        }

        addUserAddress(addressList, addressNew);
    }

    @Override
    public List<Address> searchAddresses(String query) {
        //todo implement elasticSearch logic
        return Collections.singletonList(MOCK_ADDRESS);
    }

    private void addUserAddress(List<Address> addressList, Address addressNew) {
        boolean isAddressExists =  addressList.stream().anyMatch(a -> a.getAddress().equals(addressNew.getAddress()));
        if (!isAddressExists) {
            addressList.stream().filter(Address::isLast).findFirst().ifPresent((a) -> a.setLast(false));
            addressList.add(addressNew);
            addressRepo.save(addressNew);
        }
    }



}
