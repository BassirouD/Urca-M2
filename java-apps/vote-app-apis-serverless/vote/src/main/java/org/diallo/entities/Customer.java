package org.diallo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Customer {
    private String name;
    private String email;
    private Long mobile;
    private String id;
}
