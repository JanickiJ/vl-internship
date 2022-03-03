package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class ReceiptService {

    public Receipt generate(Basket basket) {
        return getReceipt(basket);
    }

    private Receipt getReceipt(Basket basket) {
        Map<Product, Integer> productQuantities = new HashMap<>();
        basket.getProducts().forEach(product -> {
            Integer productQuantity = productQuantities.get(product);
            productQuantities.put(product, productQuantity == null ? 1 : productQuantity + 1);
        });

        List<ReceiptEntry> receiptEntries = productQuantities
                .entrySet().stream()
                .map(entry -> new ReceiptEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return new Receipt(receiptEntries);
    }
}
