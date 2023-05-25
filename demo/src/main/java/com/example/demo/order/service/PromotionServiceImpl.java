package com.example.demo.order.service;

import com.example.demo.Utils;
import com.example.demo.order.*;
import com.example.demo.order.repository.PromotionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final Utils utils;

    public PromotionServiceImpl(PromotionRepository promotionRepository, Utils utils) {
        this.promotionRepository = promotionRepository;
        this.utils = utils;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Promotion> inquireOngoingPromotions() {

        //return promotionRepository.findOngoingPromotions();
        return utils.getMockedPromotionsData();
    }


}
