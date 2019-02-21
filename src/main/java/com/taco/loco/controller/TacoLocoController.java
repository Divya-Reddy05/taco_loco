package com.taco.loco.controller;

import com.taco.loco.baseframework.controller.BaseController;
import com.taco.loco.baseframework.exception.BaseException;
import com.taco.loco.baseframework.exception.BusinessException;
import com.taco.loco.baseframework.exception.enums.ApiErrors;
import com.taco.loco.service.TacoLocoService;
import com.taco.loco.service.dto.OrderItem;
import com.taco.loco.validation.OrderItemsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiRoute.BASE_MAPPING)
public class TacoLocoController extends BaseController {

    @Autowired
    TacoLocoService tacoLocoService;

    private static final Logger logger = LoggerFactory.getLogger(TacoLocoController.class);

    @RequestMapping(value = ApiRoute.MAKE_ORDER, method = RequestMethod.POST)
    public ResponseEntity<Object> makeOrder(@RequestBody List<OrderItem> orderItemList, BindingResult
            bindingResult) throws BaseException {
        logger.info("creatng order : {}", orderItemList);
        StringBuilder stringBuilder = new StringBuilder();
        new OrderItemsValidator().validate(orderItemList, bindingResult);
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.stream().forEach(error -> {
                stringBuilder.append(String.format("%s %s", new Object[]{error.getField(), error.getDefaultMessage()})).append(",");
            });
            throw new BusinessException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES, stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(",")).toString());
        }
        return new ResponseEntity<>(tacoLocoService.makeOrder(orderItemList), HttpStatus.OK);
    }

}