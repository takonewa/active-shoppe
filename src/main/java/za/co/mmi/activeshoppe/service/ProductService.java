package za.co.mmi.activeshoppe.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import za.co.mmi.activeshoppe.data.model.Product;
import za.co.mmi.activeshoppe.data.repo.ProductRepo;
import za.co.mmi.activeshoppe.service.exception.InsufficientQuantityException;
import za.co.mmi.activeshoppe.service.exception.ProductNotFoundException;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public void createProduct(Product product) {
        product.setId(null);
        repo.save(product);
    }

    public void updateProduct(Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = repo.findById(product.getId());
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException();
        }
        repo.save(product);
    }

    public Product getProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = repo.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException();
        }
        return (optionalProduct.get());
    }

    public void reduceQuantity(Long productId, BigInteger quantity) throws InsufficientQuantityException, ProductNotFoundException {
        Optional<Product> optionalProduct = repo.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException();
        }
        Product product = optionalProduct.get();
        if (product.getQuantity().compareTo(quantity) < 0) {
            throw new InsufficientQuantityException();
        }
        product.setQuantity(product.getQuantity().subtract(quantity));
        repo.save(product);
    }
}
