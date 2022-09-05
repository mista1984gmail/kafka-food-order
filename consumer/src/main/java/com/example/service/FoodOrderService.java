package com.example.service;

import com.example.domain.dto.FoodOrderDto;

public interface FoodOrderService {
    void persistFoodOrder(FoodOrderDto foodOrderDto);
}
