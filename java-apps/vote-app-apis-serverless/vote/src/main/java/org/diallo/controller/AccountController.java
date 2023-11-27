package org.diallo.controller;

import lombok.AllArgsConstructor;
import org.diallo.entities.CustomerListResponse;
import org.diallo.entities.FirebaseUser;
import org.diallo.entities.FirebaseUserDTO;
import org.diallo.entities.FirebaseUserSignIn;
import org.diallo.services.CustomerService;
import org.diallo.services.interfaces.IAccount;
import org.springframework.web.bind.annotation.*;


import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class AccountController {
    private IAccount iAccount;
    CustomerService customerService;

    @PostMapping("/signUp")
    public FirebaseUserDTO signUp(@RequestBody FirebaseUser user) {
        return iAccount.signUp(user);
    }

    @PostMapping("/signIn")
    public FirebaseUserSignIn signIn(@RequestBody FirebaseUser user) {
        return iAccount.signIn(user);
    }

    @GetMapping("/customers")
    public CustomerListResponse getAllCustomer() throws ExecutionException, InterruptedException {
        return customerService.getCustomerList();
    }

    //https://vzskf5okii.execute-api.us-east-1.amazonaws.com/prod/api/signUp
}
