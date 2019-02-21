package com.taco.loco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taco.loco.baseframework.AbstractControllerTest;
import com.taco.loco.common.Constants;
import com.taco.loco.service.TacoLocoService;
import com.taco.loco.service.dto.OrderItem;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TacoLocoControllerTest extends AbstractControllerTest<TacoLocoController> {

    ObjectMapper objectMapper = new ObjectMapper();

    private static final String URL_MAKE_ORDER = ApiRoute.BASE_MAPPING + ApiRoute.MAKE_ORDER;

    @Override
    protected TacoLocoController createController() {
        TacoLocoController controller = new TacoLocoController();
        TacoLocoService tacoLocoService = Mockito.mock(TacoLocoService.class);
        ReflectionTestUtils.setField(controller, "tacoLocoService", tacoLocoService);
        return controller;
    }

    @Test
    public void testMakeOrder_error_400_zero_qty() throws Exception {
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemName("Any item name");
        orderItem.setItemQty(0);
        orderItemList.add(orderItem);
        mockMvc.perform(
                postWithBody(URL_MAKE_ORDER, objectMapper.writeValueAsString(orderItemList))
                        .header(Constants.KEY_CONTENT_TYPE, "application/json")
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void testMakeOrder_error_400_name_empty() throws Exception {
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemName(" ");
        orderItem.setItemQty(1);
        orderItemList.add(orderItem);
        mockMvc.perform(
                postWithBody(URL_MAKE_ORDER, objectMapper.writeValueAsString(orderItemList))
                        .header(Constants.KEY_CONTENT_TYPE, "application/json")
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void testMakeOrder_error_400_name_null() throws Exception {
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemName(null);
        orderItem.setItemQty(1);
        orderItemList.add(orderItem);
        mockMvc.perform(
                postWithBody(URL_MAKE_ORDER, objectMapper.writeValueAsString(orderItemList))
                        .header(Constants.KEY_CONTENT_TYPE, "application/json")
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void testMakeOrder_success() throws Exception {
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemName("Any item name");
        orderItem.setItemQty(1);
        orderItemList.add(orderItem);
        mockMvc.perform(
                postWithBody(URL_MAKE_ORDER, objectMapper.writeValueAsString(orderItemList))
                        .header(Constants.KEY_CONTENT_TYPE, "application/json")
        ).andExpect(status().isOk());
    }

}
