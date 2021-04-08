package za.co.mmi.activeshoppe.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.mmi.activeshoppe.data.model.Product;
import za.co.mmi.activeshoppe.service.ProductService;
import za.co.mmi.activeshoppe.service.exception.ProductNotFoundException;

import java.util.List;


@RestController
@AllArgsConstructor
@Api(tags = "Products Resource", value = "")
@RequestMapping(value = "api/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

    private ProductService productService;

    @GetMapping("/")
    @ApiOperation(value = "List All Products")
    public List<Product> allProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{product-id}")
    @ApiOperation(value = "Get Product By UUID")
    public Product getProduct(@PathVariable("product-id") Long productId) throws ProductNotFoundException {
        return productService.getProduct(productId);
    }

    @PostMapping("/")
    @ApiOperation(value = "Add New Product")
    public ResponseEntity addProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    @ApiOperation(value = "Update Existing Product")
    public ResponseEntity updateProduct(@RequestBody Product product) throws ProductNotFoundException {
        productService.updateProduct(product);
        return ResponseEntity.ok().build();
    }
}
