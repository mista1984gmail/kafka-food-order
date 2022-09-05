package com.example.service;

import com.example.domain.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FoodOrderService {
    String createFoodOrder(FoodOrder foodOrder) throws JsonProcessingException;

}
