package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final DiscountService discountService;

    @GetMapping(":countDiscountedReceipt")
    public ResponseEntity<Receipt> countDiscountedReceipt(@RequestBody Basket basket) {
        return ResponseEntity.ok(discountService.applyDiscounts(receiptService.generate(basket)));
    }
}
