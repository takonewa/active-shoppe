package za.co.mmi.activeshoppe.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import za.co.mmi.activeshoppe.service.exception.CustomerNotFoundException;
import za.co.mmi.activeshoppe.service.exception.InsufficientBalanceException;
import za.co.mmi.activeshoppe.service.exception.InsufficientQuantityException;
import za.co.mmi.activeshoppe.service.exception.ProductNotFoundException;

@Controller
@ControllerAdvice
public class ExceptionHandlerResource {
    @GetMapping
    public String index() {
        return ("redirect:/swagger-ui/index.html");
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity customer() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity product() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity balance() {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Customer has no enough balance to make a purchase");
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity quantity() {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("There are no enough product to make a purchase");
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity notFount(RuntimeException t) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t.getClass().getSimpleName());
    }
}
