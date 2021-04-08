package za.co.mmi.activeshoppe.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import za.co.mmi.activeshoppe.data.model.CartItem;
import za.co.mmi.activeshoppe.data.model.Product;
import za.co.mmi.activeshoppe.data.repo.CartItemRepo;
import za.co.mmi.activeshoppe.service.model.Cart;
import za.co.mmi.activeshoppe.service.model.CartEntry;
import za.co.mmi.activeshoppe.service.model.PurchaseRequest;
import za.co.mmi.activeshoppe.service.exception.CustomerNotFoundException;
import za.co.mmi.activeshoppe.service.exception.InsufficientBalanceException;
import za.co.mmi.activeshoppe.service.exception.InsufficientQuantityException;
import za.co.mmi.activeshoppe.service.exception.ProductNotFoundException;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log
@Component
@AllArgsConstructor
public class PurchaseService {

    private CartItemRepo cartItemRepo;
    private ProductService productService;
    private CustomerService customerService;

    public Cart getCart(Long customerId) {
        List<CartEntry> items = StreamSupport
                .stream(cartItemRepo.findByCustomer(customerId).spliterator(), false)
                .map(e -> modelToCartEntry(e))
                .collect(Collectors.toList());
        return Cart.builder().items(items)
                .totalPrice(totalPrice(items)).build();
    }

    private CartEntry modelToCartEntry(CartItem item) {
        CartEntry cartEntry = new CartEntry();
        cartEntry.setCode(item.getId());
        cartEntry.setName(item.getProductName());
        cartEntry.setQuantity(item.getQuantity());
        cartEntry.setQuantity(item.getQuantity());
        cartEntry.setTotalPrice(item.getTotalPrice());
        return (cartEntry);
    }

    private BigInteger totalPrice(List<CartEntry> items) {
        return items.stream()
                .map(e -> e.getTotalPrice())
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    public void buy(Long customerId, PurchaseRequest purchaseRequest) throws CustomerNotFoundException, InsufficientBalanceException, ProductNotFoundException, InsufficientQuantityException {
        Product product = productService.getProduct(purchaseRequest.getProductId());
        BigInteger price = product.getPrice().multiply(purchaseRequest.getQuantity());
        customerService.reduceBalance(customerId, price);
        productService.reduceQuantity(product.getId(), purchaseRequest.getQuantity());
        CartItem item = new CartItem();
        item.setTotalPrice(price);
        item.setProduct(product.getId());
        item.setProductName(product.getName());
        item.setCustomer(customerId);
        item.setQuantity(purchaseRequest.getQuantity());
        cartItemRepo.save(item);
    }
}
