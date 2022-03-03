package com.virtuslab.internship.receipt;

import com.virtuslab.internship.discount.Discount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;

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
