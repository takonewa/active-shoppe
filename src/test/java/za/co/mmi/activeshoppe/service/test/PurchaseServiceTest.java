package za.co.mmi.activeshoppe.service.test;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import za.co.mmi.activeshoppe.data.model.Customer;
import za.co.mmi.activeshoppe.data.model.Product;
import za.co.mmi.activeshoppe.data.repo.CustomerRepo;
import za.co.mmi.activeshoppe.data.repo.ProductRepo;
import za.co.mmi.activeshoppe.service.CustomerService;
import za.co.mmi.activeshoppe.service.ProductService;
import za.co.mmi.activeshoppe.service.exception.InsufficientBalanceException;
import za.co.mmi.activeshoppe.service.exception.InsufficientQuantityException;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Log
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
public class PurchaseServiceTest {
    @InjectMocks
    private ProductService productService;
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private ProductRepo productRepo;


    @Test
    public void out_of_stock() {
        Product product = new Product(1L, "A4 Book", BigInteger.valueOf(0L), BigInteger.valueOf(0L));
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        InsufficientQuantityException exception = assertThrows(InsufficientQuantityException.class, () -> {
            productService.reduceQuantity(1L, BigInteger.valueOf(20L));
        });
        assertNotNull(exception);
    }


    @Test
    public void insufficient_points() {
        Customer customer = new Customer(1L, "TK", BigInteger.valueOf(0L));
        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
        InsufficientBalanceException exception = assertThrows(InsufficientBalanceException.class, () -> {
            customerService.reduceBalance(1L, BigInteger.valueOf(20L));
        });
        assertNotNull(exception);
    }
}
