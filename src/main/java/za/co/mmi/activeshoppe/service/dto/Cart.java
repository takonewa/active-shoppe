package za.co.mmi.activeshoppe.service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Cart {
    private List<CartEntry> items;
    private BigInteger totalPrice;
}
