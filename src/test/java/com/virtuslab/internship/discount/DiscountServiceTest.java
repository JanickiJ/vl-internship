package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountServiceTest {

    @Test
    void shouldApplyTenPercentDiscountAndFifteenPercentDiscount() throws Exception {
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var steak = productDb.getProduct("Steak");
        var butter = productDb.getProduct("Butter");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 2));
        receiptEntries.add(new ReceiptEntry(butter, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var discountService = new DiscountService();
        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = discountService.applyDiscounts(receipt);
        var expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(2))
                .add(steak.price())
                .add(butter.price())
                .multiply(BigDecimal.valueOf(0.85))
                .multiply(BigDecimal.valueOf(0.90));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }
}
