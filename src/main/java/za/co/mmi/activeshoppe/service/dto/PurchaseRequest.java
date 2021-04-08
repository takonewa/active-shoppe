package za.co.mmi.activeshoppe.service.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PurchaseRequest {
    private String productUuid;
    private BigInteger quantity;
}
