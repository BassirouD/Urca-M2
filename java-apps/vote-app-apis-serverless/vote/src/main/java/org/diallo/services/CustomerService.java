package org.diallo.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.diallo.entities.Customer;
import org.diallo.entities.CustomerListResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class CustomerService {
    CustomerListResponse customerListResponse;

    public CustomerListResponse getCustomerList() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("customer").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        List<Customer> customerList = list.stream().map((doc) -> doc.toObject(Customer.class)).toList();
        customerListResponse.setList(customerList);
        return customerListResponse;
    }

}
