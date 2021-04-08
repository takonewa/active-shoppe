package za.co.mmi.activeshoppe.service.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CartEntry {
    private Long code;
    private String name;
    private BigInteger quantity;
    private BigInteger totalPrice;
}
