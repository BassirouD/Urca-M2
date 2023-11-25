package koula.org.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import koula.org.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class CustomerService {

    CustomerCreateResponse customerCreateResponse;
    CustomerListResponse customerListResponse;
    CustomerDeleteResponse customerDeleteResponse;

    public CustomerCreateResponse createCustomer(Customer customer) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("customer").document();
        customer.setId(documentReference.getId());
        ApiFuture<WriteResult> apiFuture = documentReference.set(customer);

        customerCreateResponse.setUpdatedTime(apiFuture.get().getUpdateTime().toDate());
        customerCreateResponse.setId(customer.getId());

        return customerCreateResponse;
    }

    public CustomerListResponse getCustomerList() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("customer").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        List<Customer> customerList = list.stream().map((doc) -> doc.toObject(Customer.class)).toList();
        customerListResponse.setList(customerList);
        return customerListResponse;
    }

    public CustomerListResponse getCustomerListByKey(String key) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("customer")
                .whereGreaterThanOrEqualTo("name", key)
                .whereLessThan("name", key).get();

        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        List<Customer> customerList = list.stream().map((doc) -> doc.toObject(Customer.class)).toList();
        customerListResponse.setList(customerList);
        return customerListResponse;
    }

    public CustomerCreateResponse updateCustomer(Customer customer) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = firestore.collection("customer").document(customer.getId());

        ApiFuture<WriteResult> apiFuture = documentReference.set(customer);

        customerCreateResponse.setUpdatedTime(apiFuture.get().getUpdateTime().toDate());
        customerCreateResponse.setId(customer.getId());
        return customerCreateResponse;
    }

    public CustomerDeleteResponse deleteCustomer(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = firestore.collection("customer").document(id);
        ApiFuture<WriteResult> apiFuture = documentReference.delete();

        customerDeleteResponse.setStatus(true);
        customerDeleteResponse.setUpdatedTime(apiFuture.get().getUpdateTime().toDate());
        return customerDeleteResponse;
    }

}
