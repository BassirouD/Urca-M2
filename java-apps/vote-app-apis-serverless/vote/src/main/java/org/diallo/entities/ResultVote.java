package org.diallo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResultVote {
    private String nameCandidat;
    private int nbrVote;
    private double pourcentage;
}
