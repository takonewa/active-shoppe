package za.co.mmi.activeshoppe.data.repo;

import za.co.mmi.activeshoppe.data.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {
    Product findById(String clientId);
    Product findByCode(String clientId);
}
