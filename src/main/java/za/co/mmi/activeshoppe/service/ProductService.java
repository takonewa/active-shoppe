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
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return StreamSupport
                .stream(productRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public void createProduct(Product product) {
        product.setId(null);
        productRepo.save(product);
    }

    public void updateProduct(Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepo.findById(product.getId());
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException();
        }
        productRepo.save(product);
    }

    public Product getProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException();
        }
        return (optionalProduct.get());
    }

    public void reduceQuantity(Long productId, BigInteger quantity) throws InsufficientQuantityException, ProductNotFoundException {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException();
        }
        Product product = optionalProduct.get();
        if (product.getQuantity().compareTo(quantity) < 0) {
            throw new InsufficientQuantityException();
        }
        product.setQuantity(product.getQuantity().subtract(quantity));
        productRepo.save(product);
    }
}
