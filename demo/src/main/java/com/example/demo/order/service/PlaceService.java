package com.example.demo.order.service;

import com.example.demo.order.Place;
import org.springframework.data.domain.Page;

public interface PlaceService {
    Page<Place> findPlaces(int pageNo, int pageSize);
}
