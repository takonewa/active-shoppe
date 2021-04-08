package za.co.mmi.activeshoppe.service.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ProductDTO {
    private String uuid;
    private String name;
    private BigInteger price;
    private BigInteger quantity;
}
