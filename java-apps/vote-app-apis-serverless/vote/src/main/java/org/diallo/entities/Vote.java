package org.diallo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Vote {
    private String id;
    private String userEmail;
    private String candidateId;
}
