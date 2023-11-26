package org.diallo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseUserSignIn {
    @JsonProperty("email")
    private String email;
    @JsonProperty("idToken")
    private String idToken;
}
