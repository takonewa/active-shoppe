package za.co.mmi.activeshoppe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import za.co.mmi.activeshoppe.data.model.Customer;
import za.co.mmi.activeshoppe.data.repo.CustomerRepo;
import za.co.mmi.activeshoppe.service.dto.CustomerDTO;
import za.co.mmi.activeshoppe.service.exception.CustomerNotFoundException;
import za.co.mmi.activeshoppe.service.exception.InsufficientBalanceException;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class CustomerService {
    private CustomerRepo repo;

    public List<CustomerDTO> getAllProducts() {
        return StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .map(e -> modelToDTO(e))
                .collect(Collectors.toList());
    }

    private CustomerDTO modelToDTO(Customer customer) {
        return null;
    }

    public void add(CustomerDTO customerDTO) {
        Customer customer = dtoToModel(customerDTO);
        customer.setCode(UUID.randomUUID().toString());
        repo.save(customer);
    }

    private Customer dtoToModel(CustomerDTO customerDTO) {
        return null;
    }

    public void update(CustomerDTO customerDTO) throws CustomerNotFoundException {
        Customer customer = repo.findByCode(customerDTO.getUuid());
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        customer.setName(customerDTO.getName());
        customer.setBalance(customerDTO.getBalance());
        repo.save(customer);
    }

    public void reduceBalance(String code, BigInteger amount) throws InsufficientBalanceException, CustomerNotFoundException {
        Customer customer = repo.findByCode(code);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        if (customer.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        customer.setBalance(customer.getBalance().subtract(amount));
        repo.save(customer);
    }

    public CustomerDTO get(String customerId) throws CustomerNotFoundException {
        Customer customer = repo.findByCode(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return modelToDTO(customer);
    }
}
