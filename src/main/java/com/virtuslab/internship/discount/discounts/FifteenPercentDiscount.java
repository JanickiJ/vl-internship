package com.virtuslab.internship.discount.discounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.virtuslab.internship.discount.Discount;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.springframework.boot.jackson.JsonComponent;

import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;

@JsonComponent
public class FifteenPercentDiscount extends Discount {
    protected static final String NAME = "FifteenPercentDiscount";
    protected static final BigDecimal percent = BigDecimal.valueOf(0.15);

    @Override
    protected boolean shouldApply(Receipt receipt) {
        return receipt.entries().stream()
                .filter((receiptEntry -> receiptEntry.product().type().isDiary()))
                .map(ReceiptEntry::quantity)
                .mapToInt(Integer::intValue)
                .sum() > 3;
    }

    @JsonProperty
    @Override
    public String getName() {
        return NAME;
    }

    @JsonProperty
    @Override
    public BigDecimal getPercent() {
        return percent;
    }
}