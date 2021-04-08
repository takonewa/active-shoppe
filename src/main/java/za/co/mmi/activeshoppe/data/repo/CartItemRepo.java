package za.co.mmi.activeshoppe.data.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import za.co.mmi.activeshoppe.data.model.CartItem;

public interface CartItemRepo extends PagingAndSortingRepository<CartItem, Long> {
    Iterable<CartItem> findByCustomer(Long customerId);
}
