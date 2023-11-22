package koula.org.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import koula.org.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class CustomerService {

    CustomerCreateResponse customerCreateResponse;

    public CustomerCreateResponse createCustomer(Customer customer) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("customer").document();
        customer.setId(documentReference.getId());
        ApiFuture<WriteResult> apiFuture = documentReference.set(customer);

        customerCreateResponse.setUpdatedTime(apiFuture.get().getUpdateTime().toDate());
        customerCreateResponse.setId(customer.getId());

        return customerCreateResponse;

    }
}
