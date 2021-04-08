package za.co.mmi.activeshoppe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import za.co.mmi.activeshoppe.data.model.CartItem;
import za.co.mmi.activeshoppe.data.repo.CartItemRepo;
import za.co.mmi.activeshoppe.service.dto.Cart;
import za.co.mmi.activeshoppe.service.dto.CartEntry;
import za.co.mmi.activeshoppe.service.dto.CustomerDTO;
import za.co.mmi.activeshoppe.service.dto.ProductDTO;
import za.co.mmi.activeshoppe.service.dto.PurchaseRequest;
import za.co.mmi.activeshoppe.service.exception.CustomerNotFoundException;
import za.co.mmi.activeshoppe.service.exception.InsufficientBalanceException;
import za.co.mmi.activeshoppe.service.exception.InsufficientQuantityException;
import za.co.mmi.activeshoppe.service.exception.ProductNotFoundException;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class PurchaseService {

    private CartItemRepo repo;
    private ProductService productService;
    private CustomerService customerService;

    public Cart getCart(String customerId) {
        List<CartEntry> items = StreamSupport
                .stream(repo.findByCustomer(customerId).spliterator(), false)
                .map(e -> modelToCartEntry(e))
                .collect(Collectors.toList());
        return Cart.builder().items(items)
                .totalPrice(totalPrice(items)).build();
    }

    private CartEntry modelToCartEntry(CartItem item) {
        return null;
    }

    private BigInteger totalPrice(List<CartEntry> items) {
        return items.stream()
                .map(e -> e.getTotalPrice())
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    public void buy(String customerId, PurchaseRequest purchaseRequest) throws CustomerNotFoundException, InsufficientBalanceException, ProductNotFoundException, InsufficientQuantityException {
        CustomerDTO customerDTO = customerService.get(customerId);
        ProductDTO product = productService.getProduct(purchaseRequest.getProductUuid());
        BigInteger price = product.getPrice().multiply(purchaseRequest.getQuantity());
        customerService.reduceBalance(customerDTO.getUuid(), price);
        productService.reduceQuantity(product.getUuid(), purchaseRequest.getQuantity());
        CartItem item = new CartItem();
        item.setTotalPrice(price);
        item.setProduct(product.getUuid());
        item.setProductName(product.getName());
        item.setCustomer(customerDTO.getUuid());
        item.setQuantity(purchaseRequest.getQuantity());
        repo.save(item);
    }
}
