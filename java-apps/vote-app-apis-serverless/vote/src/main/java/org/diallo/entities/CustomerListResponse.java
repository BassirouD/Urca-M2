package org.diallo.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class CustomerListResponse {
    private List<Customer> list;
}
