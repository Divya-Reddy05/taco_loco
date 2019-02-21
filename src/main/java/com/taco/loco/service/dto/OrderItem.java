package com.taco.loco.service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Strings;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonPropertyOrder({"itemName", "itemQty", "itemPrice"})
public class OrderItem implements Serializable {
    private String itemName;
    private int itemQty;
    private String itemPrice;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        if (itemName != null) {
            this.itemName = itemName.trim();
        } else {
            this.itemName = itemName;
        }
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
