package koula.org.firebase;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class CustomerCreateResponse {
    private String id;
    private Date updatedTime;
}
