package com.taco.loco.service.impl;

import com.taco.loco.baseframework.exception.BusinessException;
import com.taco.loco.baseframework.exception.enums.ApiErrors;
import com.taco.loco.service.TacoLocoService;
import com.taco.loco.service.dto.OrderItem;
import com.taco.loco.service.dto.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class TacoLocoServiceImpl implements TacoLocoService {
    private static final Logger logger = LoggerFactory.getLogger(TacoLocoService.class);
    private static final String CURRENCY_SYMBOL = "$";
    public HashMap<String, Double> itemMasterData = new HashMap<>();

    @PostConstruct
    public void loadMasterData() {
        logger.info("Loading master data");
        itemMasterData.put("Veggie Taco".toUpperCase(), 2.50);
        itemMasterData.put("Chicken Taco".toUpperCase(), 3.00);
        itemMasterData.put("Beef Taco".toUpperCase(), 3.00);
        itemMasterData.put("Chorizo Taco".toUpperCase(), 3.50);
        logger.info("Loaded master data");
    }

    @Override
    public OrderResponse makeOrder(List<OrderItem> orderItemList) throws BusinessException {
        return calculateOrderPrice(orderItemList);
    }

    protected OrderResponse calculateOrderPrice(List<OrderItem> orderItemList) throws BusinessException {
        OrderResponse orderResponse = new OrderResponse();
        logger.info("Calculating total order price");
        int totalQty = 0;
        double totalPrice = 0;
        double discountPrice = 0;
        double netPrice = 0;
        for (OrderItem orderItem : orderItemList) {
            totalQty += orderItem.getItemQty();
            if (!itemMasterData.containsKey(orderItem.getItemName().toUpperCase())) {
                logger.error("Invalid Item provided in the request - %s", orderItem.getItemName());
                throw new BusinessException(ApiErrors.INVALID_ITEM);
            }
            double itemPrice = itemMasterData.get(orderItem.getItemName().toUpperCase());
            orderItem.setItemPrice(CURRENCY_SYMBOL + itemPrice);
            totalPrice += orderItem.getItemQty() * itemPrice;
            netPrice = totalPrice;
        }
        if (totalQty >= 4) {
            discountPrice = totalPrice / 5;
            netPrice = totalPrice - discountPrice;
        }
        orderResponse.setTotalPrice(CURRENCY_SYMBOL + totalPrice);
        orderResponse.setDiscountPrice(CURRENCY_SYMBOL + discountPrice);
        orderResponse.setNetPrice(CURRENCY_SYMBOL + netPrice);
        orderResponse.setTotalItems(orderItemList.size());
        orderResponse.setOrderedItems(orderItemList);

        logger.info("Order price calculated");
        return orderResponse;
    }

}
