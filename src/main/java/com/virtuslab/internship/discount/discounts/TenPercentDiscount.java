package com.virtuslab.internship.discount.discounts;

import com.virtuslab.internship.discount.Discount;
import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public class TenPercentDiscount extends Discount {

    @Override
    protected boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) >= 0;
    }

    @Override
    protected BigDecimal getPercent() {
        return BigDecimal.valueOf(0.1);
    }

}
