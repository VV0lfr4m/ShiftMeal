package com.example.demo;

import com.example.demo.order.*;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UtilsImpl implements Utils {
    private static final String NAME_DELIMITER = " ";
    //private final UserService userService;

    /*public UtilsImpl(UserService userService) {
        this.userService = userService;
    }*/

    public User inquireLoggedInUser() {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else {
            String username = authentication.getName();
            User user = userService.findUserByFullName(username.split(NAME_DELIMITER)[0], username.split(" ")[1]);

            return user;
        }*/
        return null;
    }
    public Long inquireLoggedInUserId() {
        return 1L;
    }

    public List<Promotion> getMockedPromotionsData() {
        List<Meal> meals = Collections.singletonList(
                Meal.builder()
                        .id(1l)
                        .type("Dinner")
                        .name("Spagetti")
                        .image(new byte[]{})
                        .description("Mamma Mia!")
                        .price(200.0)
                        .rating(4.9)
                        .addons(Collections.singletonList(new Addon(1L, "Ktchp", 15.0)))
                        .amount(2)
                        .build());

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 12);
        List<Place> places = Collections.singletonList(
                Place.builder()
                        .id(1l)
                        .additionalInfo("Welcome!")
                        .addressId(1l)
                        .category("Italian")
                        .deliveryId(1l)
                        .name("Mamma Mia!")
                        .description("We offer the best italian meals!")
                        .rating(4.9)
                        .meals(meals)
                        .workTime(Collections.singletonList(new WorkTime(1l, "Sunday", new Date(), instance.getTime())))
                        .build());

        return Collections.singletonList(
                new Promotion(1l,
                        "Delivery Discount",
                        places,
                        true));
    }
}
