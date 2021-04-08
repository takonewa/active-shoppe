package za.co.mmi.activeshoppe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import za.co.mmi.activeshoppe.data.model.Customer;
import za.co.mmi.activeshoppe.data.repo.CustomerRepo;
import za.co.mmi.activeshoppe.service.exception.CustomerNotFoundException;
import za.co.mmi.activeshoppe.service.exception.InsufficientBalanceException;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class CustomerService {
    private CustomerRepo customerRepo;

    public List<Customer> getAllCustomers() {
        return StreamSupport
                .stream(customerRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void creatCustomer(Customer customer) {
        customer.setId(null);
        customerRepo.save(customer);
    }


    public void updateCustomer(Customer newCustomer) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = customerRepo.findById(newCustomer.getId());
        if (!optionalCustomer.isPresent()) {
            throw new CustomerNotFoundException();
        }
        customerRepo.save(newCustomer);
    }

    public void reduceBalance(Long customerId, BigInteger amount) throws InsufficientBalanceException, CustomerNotFoundException {
        final Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            throw new CustomerNotFoundException();
        }
        Customer customer = optionalCustomer.get();
        if (customer.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        customer.setBalance(customer.getBalance().subtract(amount));
        customerRepo.save(customer);
    }

    public Customer getCustomer(Long customerId) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        if (!customerOptional.isPresent()) {
            throw new CustomerNotFoundException();
        }
        return (customerOptional.get());
    }
}
