package com.example.service.impl;

import com.example.domain.FoodOrder;
import com.example.service.FoodOrderService;
import com.example.service.producer.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodOrderServiceImpl implements FoodOrderService {
    private final Producer producer;

    @Autowired
    public FoodOrderServiceImpl(Producer producer) {
        this.producer = producer;
    }
    @Override
    public String createFoodOrder(FoodOrder foodOrder) throws JsonProcessingException {
        return producer.sendMessage(foodOrder);
    }

}
