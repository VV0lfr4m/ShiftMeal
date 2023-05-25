package com.example.demo.home;

import com.example.demo.order.Place;
import com.example.demo.order.Promotion;
import com.example.demo.order.service.PlaceService;
import com.example.demo.order.service.PromotionService;
import com.example.demo.user.entity.Address;
import com.example.demo.user.service.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final AddressService addressService;
    private final PromotionService promotionService;
    private final PlaceService placeService;

    public HomeController(AddressService addressService, PromotionService promotionService, PlaceService placeService) {
        this.addressService = addressService;
        this.promotionService = promotionService;
        this.placeService = placeService;
    }


    @GetMapping("/inquireDeliveryAddress")
    ResponseEntity<Address> inquireDeliveryAddress() {
        Address deliveryAddress = addressService.inquireDeliveryAddress();

        return ResponseEntity.status(HttpStatus.OK).body(deliveryAddress);
    }

    @GetMapping("/inquireOngoingPromotions")
    ResponseEntity<List<Promotion>> inquireOngoingPromotions() {
        List<Promotion> promotions = promotionService.inquireOngoingPromotions();

        return ResponseEntity.status(HttpStatus.OK).body(promotions);
    }

    @GetMapping("/inquirePlaces")
    ResponseEntity<Page<Place>> inquirePlaces(@RequestParam Integer pageNo, Integer pageSize) {
        Page<Place> places = placeService.findPlaces(pageNo, pageSize);

        return ResponseEntity.status(HttpStatus.OK).body(places);
    }
}
