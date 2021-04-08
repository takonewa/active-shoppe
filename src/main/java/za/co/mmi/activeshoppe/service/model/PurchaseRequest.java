package za.co.mmi.activeshoppe.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Data
@AllArgsConstructor
public class PurchaseRequest {
    @NotBlank(message="Specify the product ID")
    private Long productId;
    @NotBlank(message="Specify the product quantity")
    private BigInteger quantity;
}
