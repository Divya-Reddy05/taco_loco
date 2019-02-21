package com.taco.loco.service;

import com.taco.loco.baseframework.exception.BusinessException;
import com.taco.loco.service.dto.OrderItem;
import com.taco.loco.service.dto.OrderResponse;
import com.taco.loco.service.impl.TacoLocoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class TacoLocoServiceTest {
    @InjectMocks
    TacoLocoServiceImpl tacoLocoService = new TacoLocoServiceImpl();
    private static final String CURRENCY_SYMBOL = "$";
    private HashMap<String, Double> itemMasterData = new HashMap<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.tacoLocoService.loadMasterData();
        itemMasterData.put("Veggie Taco".toUpperCase(), 2.50);
        itemMasterData.put("Chicken Taco".toUpperCase(), 3.00);
        itemMasterData.put("Beef Taco".toUpperCase(), 3.00);
        itemMasterData.put("Chorizo Taco".toUpperCase(), 3.50);
    }

    @Test(expected = BusinessException.class)
    public void testMakeOrder__Error_NoSupportedItem() throws BusinessException {
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemName("UNAVAILABLE ITEM");
        orderItem.setItemQty(1);
        orderItemList.add(orderItem);
        tacoLocoService.makeOrder(orderItemList);
    }


    @Test
    public void testMakeOrder__Success_NoDiscount() throws BusinessException {
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemName(itemMasterData.keySet().stream().findFirst().get());
        orderItem.setItemQty(1);
        orderItemList.add(orderItem);
        OrderResponse orderResponse = tacoLocoService.makeOrder(orderItemList);
        assertThat(orderResponse.getTotalItems(), is(equalTo(orderItemList.size())));
        assertThat(orderResponse.getDiscountPrice(), is(equalTo(CURRENCY_SYMBOL + 0.0)));
        assertThat(orderResponse.getTotalPrice(), is(equalTo(CURRENCY_SYMBOL + 3.0)));
        assertThat(orderResponse.getNetPrice(), is(equalTo(CURRENCY_SYMBOL + 3.0)));
    }

    @Test
    public void testMakeOrder__Success_WithDiscount_AllItems() throws BusinessException {
        List<OrderItem> orderItemList = new ArrayList<>();
        itemMasterData.keySet().stream().forEach(
                key -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setItemName(key);
                    orderItem.setItemQty(1);
                    orderItemList.add(orderItem);
                }
        );
        OrderResponse orderResponse = tacoLocoService.makeOrder(orderItemList);
        assertThat(orderResponse.getTotalItems(), is(equalTo(orderItemList.size())));
        assertThat(orderResponse.getDiscountPrice(), is(equalTo(CURRENCY_SYMBOL + 2.4)));
        assertThat(orderResponse.getTotalPrice(), is(equalTo(CURRENCY_SYMBOL + 12.0)));
        assertThat(orderResponse.getNetPrice(), is(equalTo(CURRENCY_SYMBOL + 9.6)));

    }

    @Test
    public void testMakeOrder__Success_WithDiscount_SelectedItems() throws BusinessException {
        List<OrderItem> orderItemList = new ArrayList<>();
        itemMasterData.keySet().stream().forEach(
                key -> {
                    if (key.contains("Chicken".toUpperCase()) || key.contains("Chorizo".toUpperCase())) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setItemName(key);
                        orderItem.setItemQty(3);
                        orderItemList.add(orderItem);
                    }
                }
        );
        OrderResponse orderResponse = tacoLocoService.makeOrder(orderItemList);
        assertThat(orderResponse.getTotalItems(), is(equalTo(orderItemList.size())));
        assertThat(orderResponse.getDiscountPrice(), is(equalTo(CURRENCY_SYMBOL + 3.9)));
        assertThat(orderResponse.getTotalPrice(), is(equalTo(CURRENCY_SYMBOL + 19.5)));
        assertThat(orderResponse.getNetPrice(), is(equalTo(CURRENCY_SYMBOL + 15.6)));
    }

}
