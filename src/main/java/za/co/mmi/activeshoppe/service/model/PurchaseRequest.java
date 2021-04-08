package za.co.mmi.activeshoppe.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class PurchaseRequest {
    private Long productId;
    private BigInteger quantity;
}
