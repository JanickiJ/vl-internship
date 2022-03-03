package com.virtuslab.internship.discount;

import com.virtuslab.internship.discount.discounts.FifteenPercentDiscount;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FifteenPercentDiscountTest {

    @Test
    void shouldApplyDiscount() throws Exception {
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var steak = productDb.getProduct("Steak");
        var butter = productDb.getProduct("Butter");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 2));
        receiptEntries.add(new ReceiptEntry(butter, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var discount = new FifteenPercentDiscount();
        var receipt = new Receipt(receiptEntries);
        var receiptAfterDiscount = discount.apply(receipt);
        var expectedTotalPrice = cheese.price().multiply(BigDecimal.valueOf(2))
                .add(steak.price())
                .add(butter.price())
                .multiply(BigDecimal.valueOf(0.85));

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApplyDiscount() throws Exception {
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var steak = productDb.getProduct("Steak");
        var butter = productDb.getProduct("Butter");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 1));
        receiptEntries.add(new ReceiptEntry(butter, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var receipt = new Receipt(receiptEntries);
        var discount = new FifteenPercentDiscount();
        var receiptAfterDiscount = discount.apply(receipt);
        var expectedTotalPrice = cheese.price()
                .add(steak.price())
                .add(butter.price());

        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }
}