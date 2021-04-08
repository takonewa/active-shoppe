package za.co.mmi.activeshoppe.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.math.BigInteger;


@Data
@Entity
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item")
    @SequenceGenerator(name = "cart_item", sequenceName = "cart_item_seq", allocationSize = 1)
    private Long id;
    private Long product;
    private Long customer;
    private String productName;
    private BigInteger quantity;
    private BigInteger totalPrice;
}
