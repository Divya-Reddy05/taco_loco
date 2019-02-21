package com.taco.loco.service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder({"totalItems", "totalPrice", "discountPrice", "netPrice", "orderedItems"})
public class OrderResponse implements Serializable {
    private int totalItems;
    private String totalPrice;
    private String discountPrice;
    private String netPrice;
    private List orderedItems;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(String netPrice) {
        this.netPrice = netPrice;
    }

    public List getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List orderedItems) {
        this.orderedItems = orderedItems;
    }
}
