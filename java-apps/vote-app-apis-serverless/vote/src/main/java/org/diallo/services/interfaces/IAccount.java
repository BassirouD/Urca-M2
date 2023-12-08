package org.diallo.services.interfaces;

import org.diallo.entities.FirebaseUser;
import org.diallo.entities.FirebaseUserDTO;
import org.diallo.entities.FirebaseUserSignIn;
import org.diallo.tools.Response;
import org.springframework.http.HttpEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface IAccount {
    FirebaseUserDTO signUp(FirebaseUser firebaseUser);

    FirebaseUserDTO signUp2(FirebaseUser firebaseUser);

    FirebaseUserDTO signUp3(FirebaseUser firebaseUser);

    FirebaseUserSignIn signIn(FirebaseUser firebaseUser);
}
