package com.virtuslab.internship.discount.discounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.virtuslab.internship.discount.Discount;
import com.virtuslab.internship.receipt.Receipt;
import org.springframework.boot.jackson.JsonComponent;

import java.math.BigDecimal;

@JsonComponent
public class TenPercentDiscount extends Discount {
    protected static final String NAME = "TenPercentDiscount";
    protected static final BigDecimal percent = BigDecimal.valueOf(0.1);

    @Override
    protected boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) >= 0;
    }

    @Override
    @JsonProperty
    protected BigDecimal getPercent() {
        return percent;
    }

    @Override
    @JsonProperty
    public String getName() {
        return NAME;
    }
}
