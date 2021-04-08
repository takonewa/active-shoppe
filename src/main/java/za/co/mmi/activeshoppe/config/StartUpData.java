package za.co.mmi.activeshoppe.config;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import za.co.mmi.activeshoppe.data.model.Customer;
import za.co.mmi.activeshoppe.data.model.Product;
import za.co.mmi.activeshoppe.service.CustomerService;
import za.co.mmi.activeshoppe.service.ProductService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Log
@Component
@AllArgsConstructor
public class StartUpData {

    private ProductService productService;
    private CustomerService customerService;

    public void loadData() {
        getCustomers().forEach(e -> {
            customerService.creatCustomer(e);
        });
        getProducts().forEach(e -> {
            productService.createProduct(e);
        });
    }

    private List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "TK", BigInteger.valueOf(300)));
        customers.add(new Customer(2L, "James", BigInteger.valueOf(600)));
        customers.add(new Customer(3L, "Sarah", BigInteger.valueOf(3)));
        customers.add(new Customer(4L, "Dan", BigInteger.valueOf(12)));
        return (customers);
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Book A1", BigInteger.valueOf(15), BigInteger.valueOf(10)));
        products.add(new Product(2L, "Bic Pen", BigInteger.valueOf(9), BigInteger.valueOf(5)));
        products.add(new Product(3L, "72 Page A4 Book", BigInteger.valueOf(17), BigInteger.valueOf(25)));
        products.add(new Product(4L, "Book ARX", BigInteger.valueOf(21), BigInteger.valueOf(19)));
        return (products);
    }
}
