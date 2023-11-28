package org.diallo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Candidate {
    private String candidateId;
    private String name;
}
