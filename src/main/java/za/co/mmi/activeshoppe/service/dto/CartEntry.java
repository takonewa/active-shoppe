package za.co.mmi.activeshoppe.service.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CartEntry {
    private String name;
    private String code;
    private BigInteger quantity;
    private BigInteger totalPrice;
}
