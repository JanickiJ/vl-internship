package com.virtuslab.internship.discount;

import com.virtuslab.internship.discount.discounts.FifteenPercentDiscount;
import com.virtuslab.internship.discount.discounts.TenPercentDiscount;
import com.virtuslab.internship.receipt.Receipt;

import java.util.List;

public class DiscountService {
    private final List<Discount> discounts;

    public DiscountService() {
        discounts = List.of(new FifteenPercentDiscount(), new TenPercentDiscount());
    }

    public Receipt apply(Receipt receipt){
        for (Discount discount: discounts) {
            receipt = discount.apply(receipt);
        }
        return receipt;
    }
}
