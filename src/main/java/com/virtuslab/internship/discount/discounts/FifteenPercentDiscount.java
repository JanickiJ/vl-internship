package com.virtuslab.internship.discount.discounts;

import com.virtuslab.internship.discount.Discount;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;

public class FifteenPercentDiscount extends Discount {

    @Override
    protected boolean shouldApply(Receipt receipt) {
        return receipt.entries().stream()
                .filter((receiptEntry -> receiptEntry.product().isDiary()))
                .map(ReceiptEntry::quantity)
                .mapToInt(Integer::intValue)
                .sum() == 0;
    }

    @Override
    protected BigDecimal getPercent() {
        return BigDecimal.valueOf(0.15);
    }
}