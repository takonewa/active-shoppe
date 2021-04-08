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
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer")
    @SequenceGenerator(name = "customer", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    private String name;
    private BigInteger balance;
}
