package org.diallo.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.diallo.entities.Candidate;
import org.diallo.services.interfaces.ICandidatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class ICandidatServiceImpl implements ICandidatService {
    private final String firebaseUrl = "https://diallo-koula-shop-default-rtdb.firebaseio.com/";

    @Override
    public Candidate create(Candidate candidate) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("candidats").document();
        candidate.setCandidateId(documentReference.getId());
        documentReference.set(candidate);
        return candidate;
    }

    @Override
    public Candidate update(Candidate candidate) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("candidats").document(candidate.getCandidateId());
        documentReference.set(candidate);
        return candidate;
    }

    @Override
    public List<Candidate> candidatList() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("candidats").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        return list.stream().map((doc) -> doc.toObject(Candidate.class)).toList();
    }

    @Override
    public Boolean delete(String id) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("candidats").document(id);
        documentReference.delete();
        return true;
    }
}
