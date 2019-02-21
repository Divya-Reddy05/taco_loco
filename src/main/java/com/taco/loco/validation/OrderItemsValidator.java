package com.taco.loco.validation;

import com.google.common.base.Strings;
import com.taco.loco.service.dto.OrderItem;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class OrderItemsValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return List.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (o instanceof List) {
            for (OrderItem orderItem : (List<OrderItem>) o) {
                if (Strings.isNullOrEmpty(orderItem.getItemName())) {
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "Name cannot be null or empty");
                }
                if (orderItem.getItemQty() < 1) {
                    ValidationUtils.rejectIfEmpty(errors, "qty", null, "Minimum 1 quantity is required");
                }
            }
        }
    }

}
