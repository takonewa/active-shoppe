package za.co.mmi.activeshoppe.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.mmi.activeshoppe.service.PurchaseService;
import za.co.mmi.activeshoppe.service.model.Cart;
import za.co.mmi.activeshoppe.service.model.PurchaseRequest;
import za.co.mmi.activeshoppe.service.exception.CustomerNotFoundException;
import za.co.mmi.activeshoppe.service.exception.InsufficientBalanceException;
import za.co.mmi.activeshoppe.service.exception.InsufficientQuantityException;
import za.co.mmi.activeshoppe.service.exception.ProductNotFoundException;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@Api(tags = "Shopping Resource", value = "")
@RequestMapping(value = "api/v1/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingResource {

    private PurchaseService purchaseService;

    @GetMapping("/cart/{customer-id}")
    @ApiOperation(value = "Get Customer Cart", response = Cart.class)
    public Cart cart(@PathVariable("customer-id") Long customerId) {
        return purchaseService.getCart(customerId);
    }

    @PostMapping("/purchase/{customer-id}")
    @ApiOperation(value = "Buy Product", code = 200)
    public ResponseEntity purchase(@PathVariable("customer-id") Long customerId, @Valid @RequestBody PurchaseRequest request) throws CustomerNotFoundException, InsufficientBalanceException, ProductNotFoundException, InsufficientQuantityException {
        purchaseService.buy(customerId, request);
        return ResponseEntity.ok().build();
    }
}
