package org.diallo.controller;

import lombok.AllArgsConstructor;
import org.diallo.entities.FirebaseUser;
import org.diallo.entities.FirebaseUserDTO;
import org.diallo.entities.FirebaseUserSignIn;
import org.diallo.services.interfaces.IAccount;
import org.diallo.tools.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AccountController {
    private IAccount iAccount;

    @PostMapping("/signUp")
    public FirebaseUserDTO signUp(@RequestBody FirebaseUser user) {
        return iAccount.signUp(user);
    }

    @PostMapping("/signIn")
    public FirebaseUserSignIn signIn(@RequestBody FirebaseUser user) {
        return iAccount.signIn(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp2(@RequestBody FirebaseUser firebaseUser) {
        try {
            FirebaseUserDTO userDTO = iAccount.signUp2(firebaseUser);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            // Gérer les exceptions spécifiques
            if ("L'adresse e-mail existe déjà.".equals(ex.getMessage())) {
                return new ResponseEntity<>("L'adresse e-mail existe déjà.", HttpStatus.BAD_REQUEST);
            } else {
                // Gérer d'autres exceptions si nécessaire
                return new ResponseEntity<>("Erreur lors de la création de l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/signup-test")
    public Response<?> signUp3(@RequestBody FirebaseUser firebaseUser) {
        FirebaseUserDTO firebaseUserDTO = iAccount.signUp3(firebaseUser);
        return firebaseUserDTO == null ? Response.internalServerError().setErrors("Email already exists") :
                Response.created().setData(firebaseUserDTO);
    }


    //https://vzskf5okii.execute-api.us-east-1.amazonaws.com/prod/api/signUp
}
