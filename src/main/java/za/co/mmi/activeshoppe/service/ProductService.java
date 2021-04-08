package za.co.mmi.activeshoppe.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import za.co.mmi.activeshoppe.data.model.Product;
import za.co.mmi.activeshoppe.data.repo.ProductRepo;
import za.co.mmi.activeshoppe.service.dto.ProductDTO;
import za.co.mmi.activeshoppe.service.exception.InsufficientQuantityException;
import za.co.mmi.activeshoppe.service.exception.ProductNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log
@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo repo;

    public List<ProductDTO> getAllProducts() {
        return StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .map(e -> modelToDTO(e))
                .collect(Collectors.toList());
    }

    private ProductDTO modelToDTO(Product e) {
        return null;
    }

    public void addProduct(ProductDTO productDTO) {
        Product product = dtoToModel(productDTO);
        product.setCode(UUID.randomUUID().toString());
        repo.save(product);
    }

    private Product dtoToModel(ProductDTO productDTO) {
        return null;
    }

    public void updateProduct(ProductDTO productDTO) {
        Product product = repo.findByCode(productDTO.getUuid());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        repo.save(product);
    }

    public ProductDTO getProduct(String productId) throws ProductNotFoundException {
        Product product = repo.findByCode(productId);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return modelToDTO(product);
    }

    public void reduceQuantity(String code, BigInteger quantity) throws InsufficientQuantityException, ProductNotFoundException {
        Product product = repo.findByCode(code);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        if (product.getQuantity().compareTo(quantity) < 0) {
            throw new InsufficientQuantityException();
        }
        product.setQuantity(product.getQuantity().subtract(quantity));
        repo.save(product);
    }
}
