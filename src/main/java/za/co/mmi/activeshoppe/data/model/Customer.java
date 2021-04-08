package za.co.mmi.activeshoppe.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer")
    @SequenceGenerator(name = "customer", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    @NotBlank(message = "Customer name is mandatory")
    private String name;
    @NotBlank(message = "Customer balance is required")
    private BigInteger balance;
}
