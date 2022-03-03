package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public abstract class Discount {

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.ONE.subtract(this.getPercent()));
            var discounts = receipt.discounts();
            discounts.add(this);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    protected abstract boolean shouldApply(Receipt receipt);
    protected abstract BigDecimal getPercent();
}
