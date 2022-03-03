package com.virtuslab.internship.receipt;

import com.virtuslab.internship.discount.Discount;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public record Receipt(
        List<ReceiptEntry> entries,
        List<Discount> discounts,
        BigDecimal totalPrice) {

    public Receipt(List<ReceiptEntry> entries) {
        this(entries,
                new LinkedList<>(),
                entries.stream()
                        .map(ReceiptEntry::totalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }
}
