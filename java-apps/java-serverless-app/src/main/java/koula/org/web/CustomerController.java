package koula.org.web;

import koula.org.entities.Customer;
import koula.org.firebase.CustomerCreateResponse;
import koula.org.firebase.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    CustomerService customerService;

    @PostMapping("/")
    public CustomerCreateResponse createCustomer(@RequestBody Customer customer) throws ExecutionException, InterruptedException {
        return customerService.createCustomer(customer);
    }
}
