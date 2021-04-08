package za.co.mmi.activeshoppe.service;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import za.co.mmi.activeshoppe.data.repo.CustomerRepo;
import za.co.mmi.activeshoppe.service.exception.CustomerNotFoundException;

import java.util.logging.Level;

@Log
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @Mock
    private CustomerRepo repo;
    @Mock
    private ProductService productService;

    @Test
    public void invalidCustomerId() throws CustomerNotFoundException {
        //when(repo.findById(anyString())).thenReturn(null);
        //when(customerService.get(anyString())).thenThrow(new CustomerNotFoundException());
        log.log(Level.SEVERE, "==============================================");
        //customerService.get("123");
    }
}
