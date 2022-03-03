package com.virtuslab.internship.product;

import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;

public record Product(
        String name,
        Type type,
        BigDecimal price
) {

    public enum Type {
        DAIRY, FRUITS, VEGETABLES, MEAT, GRAINS
    }

    public boolean isDiary() {
        return this.type == Type.DAIRY;
    }
}
