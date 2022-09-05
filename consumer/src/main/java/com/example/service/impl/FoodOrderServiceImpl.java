package com.example.service.impl;

import com.example.domain.FoodOrder;
import com.example.domain.dto.FoodOrderDto;
import com.example.repository.FoodOrderRepository;
import com.example.service.FoodOrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodOrderServiceImpl implements FoodOrderService {
    private final FoodOrderRepository foodOrderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FoodOrderServiceImpl(FoodOrderRepository foodOrderRepository, ModelMapper modelMapper) {
        this.foodOrderRepository = foodOrderRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void persistFoodOrder(FoodOrderDto foodOrderDto) {
        FoodOrder foodOrder = modelMapper.map(foodOrderDto, FoodOrder.class);
        FoodOrder persistedFoodOrder = foodOrderRepository.save(foodOrder);

        log.info("food order persisted {}", persistedFoodOrder);
    }
}
