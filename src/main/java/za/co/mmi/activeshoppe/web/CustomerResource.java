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
import za.co.mmi.activeshoppe.service.CustomerService;
import za.co.mmi.activeshoppe.service.dto.CustomerDTO;
import za.co.mmi.activeshoppe.service.exception.CustomerNotFoundException;

import java.util.List;


@RestController
@AllArgsConstructor
@Api(tags = "Customer Resource", value = "")
@RequestMapping(value = "api/v1/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerResource {

    private CustomerService customerService;

    @GetMapping("/")
    @ApiOperation(value = "List All Customers")
    public List<CustomerDTO> listAll() {
        return customerService.getAllProducts();
    }

    @GetMapping("/{customer-id}")
    @ApiOperation(value = "Get Customer By UUID")
    public CustomerDTO getCustomer(@PathVariable("customer-id") String customerId) throws CustomerNotFoundException {
        return customerService.get(customerId);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create New Customer")
    public ResponseEntity addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.add(customerDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    @ApiOperation(value = "Update Existing Customer")
    public ResponseEntity updateCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {
        customerService.update(customerDTO);
        return ResponseEntity.ok().build();
    }
}
