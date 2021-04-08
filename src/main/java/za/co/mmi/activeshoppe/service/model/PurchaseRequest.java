package za.co.mmi.activeshoppe.service.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PurchaseRequest {
    private Long productId;
    private BigInteger quantity;
}
