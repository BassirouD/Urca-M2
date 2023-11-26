package org.diallo.controller;

import lombok.AllArgsConstructor;
import org.diallo.entities.FirebaseUser;
import org.diallo.entities.FirebaseUserDTO;
import org.diallo.entities.FirebaseUserSignIn;
import org.diallo.services.interfaces.IAccount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AccountController {
    private IAccount iAccount;
    @PostMapping("/signUp")
    public FirebaseUserDTO signUp(@RequestBody FirebaseUser user){
        return iAccount.signUp(user);
    }
    @PostMapping("/signIn")
    public FirebaseUserSignIn signIn(@RequestBody FirebaseUser user){
        return iAccount.signIn(user);
    }
}
