package com.example.demo.order.service;

import com.example.demo.Utils;
import com.example.demo.order.Place;
import com.example.demo.order.repository.PlaceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final Utils utils;

    public PlaceServiceImpl(PlaceRepository placeRepository, Utils utils) {
        this.placeRepository = placeRepository;
        this.utils = utils;
    }

    @Override
    public Page<Place> findPlaces(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return placeRepository.findAll(pageable);
        //return new PageImpl<Place>(utils.getMockedPromotionsData().get(0).getPlaces());
    }
}
