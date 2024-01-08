package diallo.koula.domain.entities;

import java.math.BigDecimal;
import java.util.*;

public class Order {
    private UUID id;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private BigDecimal price;

    public Order(UUID id, Product product) {
        this.id = id;
        this.orderItems = new ArrayList<>(Arrays.asList(new OrderItem(product)));
        this.status = OrderStatus.CREATED;
        price = product.getPrice();
    }

    public void complete() {
        status = OrderStatus.COMPLETED;
    }

    public void addOrder(Product product) {
        orderItems.add(new OrderItem(product));
        price = price.add(product.getPrice());
    }

    public void removeOrder(UUID id) {
        Optional<OrderItem> orderItem = orderItems
                .stream()
                .filter(orderItem1 -> orderItem1.getProductId() == id)
                .findFirst();
        orderItems.remove(orderItem);
        price = price.subtract(orderItem.get().getPrice());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public enum OrderStatus {
        CREATED, COMPLETED
    }
}
