package za.co.mmi.activeshoppe.data.repo;

import za.co.mmi.activeshoppe.data.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepo extends PagingAndSortingRepository<Customer, Long> {
    Customer findById(String clientId);

    Customer findByCode(String customerId);
}
