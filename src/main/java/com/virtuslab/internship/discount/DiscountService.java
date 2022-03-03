package com.virtuslab.internship.discount;

import com.virtuslab.internship.discount.discounts.FifteenPercentDiscount;
import com.virtuslab.internship.discount.discounts.TenPercentDiscount;
import com.virtuslab.internship.receipt.Receipt;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class DiscountService {
    private static final List<Discount> discounts = List.of(new FifteenPercentDiscount(), new TenPercentDiscount());

    public Receipt applyDiscounts(Receipt receipt) {
        for (Discount discount : discounts) {
            receipt = discount.apply(receipt);
        }
        return receipt;
    }
}
