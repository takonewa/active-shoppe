package za.co.mmi.activeshoppe.service.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CustomerDTO {
    private String uuid;
    private String name;
    private BigInteger balance;
}
