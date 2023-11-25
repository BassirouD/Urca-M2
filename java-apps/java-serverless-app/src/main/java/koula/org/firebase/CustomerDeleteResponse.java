package koula.org.firebase;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class CustomerDeleteResponse {
    private boolean status;
    private Date updatedTime;
}
