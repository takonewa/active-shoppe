package za.co.mmi.activeshoppe.data.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import za.co.mmi.activeshoppe.data.model.CartItem;
import za.co.mmi.activeshoppe.data.model.Customer;

public interface CartItemRepo extends PagingAndSortingRepository<CartItem, Long> {
    CartItem findById(String clientId);

    Iterable<CartItem> findByCustomer(String clientId);
}
