package gn.diallo.demohexagradle.adapters.out.postgresJDBC.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "movies")
public record MovieEntity(
        @Id
        Long id,
        String title,
        String description,
        LocalDate release,
        String director,
        @Version
        Integer version
) {
}
