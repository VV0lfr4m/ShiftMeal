package com.example.demo;

import com.example.demo.order.*;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public interface Utils {


    User inquireLoggedInUser();

    Long inquireLoggedInUserId();

    List<Promotion> getMockedPromotionsData();
}
