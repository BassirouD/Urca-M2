package org.diallo.services.interfaces;

import org.diallo.entities.FirebaseUser;
import org.diallo.entities.FirebaseUserDTO;
import org.diallo.entities.FirebaseUserSignIn;

public interface IAccount {
    FirebaseUserDTO signUp(FirebaseUser firebaseUser);

    FirebaseUserSignIn signIn(FirebaseUser firebaseUser);
}
