package za.co.mmi.activeshoppe.service.test;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import za.co.mmi.activeshoppe.data.repo.ProductRepo;
import za.co.mmi.activeshoppe.service.ProductService;
import za.co.mmi.activeshoppe.service.exception.ProductNotFoundException;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Log
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
public class PurchaseServiceTest {

    @Mock
    private ProductRepo repo;
    @Mock
    private ProductService productService;

    @Test
    public void invalidCustomerId() throws  ProductNotFoundException {
        /*when(repo.findById(anyString())).thenReturn(null);
        when(productService.getProduct(anyString())).thenThrow(new ProductNotFoundException());
        ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class, () -> {
            productService.getProduct(anyString());
        });
        assertNotNull(productNotFoundException);
        log.log(Level.SEVERE, productNotFoundException.getMessage());*/
    }
}
