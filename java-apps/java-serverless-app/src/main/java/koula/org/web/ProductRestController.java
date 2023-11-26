package koula.org.web;

import koula.org.entities.Customer;
import koula.org.entities.Product;
import koula.org.firebase.CustomerCreateResponse;
import koula.org.firebase.CustomerDeleteResponse;
import koula.org.firebase.CustomerListResponse;
import koula.org.firebase.CustomerService;
import koula.org.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductRestController {
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> productList() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Product productById(@PathVariable String id) {
        return productRepository.findById(id).get();
    }

    CustomerService customerService;

    //With firebase---------------------------------------------------------------------

    @PostMapping("/customers")
    public CustomerCreateResponse createCustomer(@RequestBody Customer customer) throws ExecutionException, InterruptedException {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/customers")
    public CustomerListResponse getAllCustomer() throws ExecutionException, InterruptedException {
        return customerService.getCustomerList();
    }

    @GetMapping("/customers/{key}")
    public CustomerListResponse getAllCustomerByKey(@PathVariable String key) throws InterruptedException, ExecutionException {
        return customerService.getCustomerListByKey(key);
    }

    @PutMapping("/customers")
    public CustomerCreateResponse updateCustomer(@RequestBody Customer customer) throws ExecutionException, InterruptedException {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customers/{id}")
    public CustomerDeleteResponse updateCustomer(@PathVariable String id) throws ExecutionException, InterruptedException {
        return customerService.deleteCustomer(id);
    }


    //https://q9fqnch84c.execute-api.us-east-1.amazonaws.com/prod/api/products

    //https://y22kuw2sf5.execute-api.us-east-1.amazonaws.com/prod

    //New version
    //https://icswanx57e.execute-api.us-east-1.amazonaws.com/prod/api/customers
}
