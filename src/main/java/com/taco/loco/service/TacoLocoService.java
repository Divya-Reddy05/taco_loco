package com.taco.loco.service;

import com.taco.loco.baseframework.exception.BusinessException;
import com.taco.loco.service.dto.OrderItem;
import com.taco.loco.service.dto.OrderResponse;

import java.util.List;

public interface TacoLocoService {
    OrderResponse makeOrder(List<OrderItem> orderItemList) throws BusinessException;
}
