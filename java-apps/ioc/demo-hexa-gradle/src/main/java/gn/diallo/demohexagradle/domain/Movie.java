package gn.diallo.demohexagradle.domain;

import java.time.LocalDate;

public record Movie(
        Long id,
        String title,
        String description,
        LocalDate release,
        String director
) {
}
