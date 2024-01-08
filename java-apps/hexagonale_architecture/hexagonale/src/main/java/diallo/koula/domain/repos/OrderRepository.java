package diallo.koula.domain.repos;

import diallo.koula.domain.entities.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Optional<Order> findById(UUID id);
    void save(Order order);
}
