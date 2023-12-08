package org.diallo.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.diallo.entities.Candidate;
import org.diallo.entities.ResultVote;
import org.diallo.entities.Statistique;
import org.diallo.entities.Vote;
import org.diallo.services.interfaces.IVoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class IVoteServiceImpl implements IVoteService {
    @Override
    public Vote voter(Vote vote) {
        Firestore firestore = FirestoreClient.getFirestore();

        // Vérifier si l'email a déjà voté
        boolean b = checkUser(vote.getUserEmail());
        if (b) {
            throw new IllegalStateException("L'utilisateur a déjà voté.");
        }else {
            DocumentReference documentReference = firestore.collection("votes").document();
            vote.setId(documentReference.getId());
            documentReference.set(vote);
            return vote;
        }
    }

    @Override
    public List<Vote> voteList() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("votes").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        return list.stream().map((doc) -> doc.toObject(Vote.class)).toList();
    }

    @Override
    public List<ResultVote> RESULT_VOTE_LIST() throws ExecutionException, InterruptedException {
        int nbrVotants = getNbrVote();

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = firestore.collection("votes").get();
        Map<String, Integer> voteCounts = new HashMap<>();
        for (DocumentSnapshot document : querySnapshotApiFuture.get().getDocuments()) {
            // Récupérez le champ candidateId de chaque document
            String candidateId = document.getString("candidateId");
            // Incrémentez le compteur pour ce candidat
            voteCounts.put(candidateId, voteCounts.getOrDefault(candidateId, 0) + 1);
        }
        // Construisez la liste de résultats du vote
        List<ResultVote> resultVotes = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
            String name = getName(entry.getKey());
            ResultVote resultVote = new ResultVote();
            double percentage = (entry.getValue() * 100.0) / nbrVotants;
            resultVote.setNameCandidat(name);
            resultVote.setNbrVote(entry.getValue());
            resultVote.setPourcentage(percentage);
            resultVotes.add(resultVote);
        }
        return resultVotes;
    }

    @Override
    public Statistique STATISTIQUE() {
        int nbrCandidat = getNbrCandidat();
        int nbrVote = getNbrVote();
        Statistique statistique = new Statistique();
        statistique.setNbrCandidat(nbrCandidat);
        statistique.setNbrVote(nbrVote);
        return statistique;
    }

    public int getNbrCandidat() {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> votesQuerySnapshot = db.collection("candidats").get();
        List<QueryDocumentSnapshot> candidatsDocuments = null;
        try {
            candidatsDocuments = votesQuerySnapshot.get().getDocuments();
        } catch (Exception e) {
            System.out.println("=========================" + e.getMessage());
        }
        int nbrCandidat = candidatsDocuments != null ? candidatsDocuments.size() : 0;
        return nbrCandidat;
    }

    public int getNbrVote() {
        Firestore db = FirestoreClient.getFirestore();
        // Récupérez les données de la collection des votes
        ApiFuture<QuerySnapshot> votesQuerySnapshot = db.collection("votes").get();
        List<QueryDocumentSnapshot> votesDocuments = null;

        try {
            votesDocuments = votesQuerySnapshot.get().getDocuments();
        } catch (Exception e) {
            System.out.println("=========================" + e.getMessage());
        }

        // Récupérez le nombre total de votes
        int totalVotes = votesDocuments != null ? votesDocuments.size() : 0;
        return totalVotes;
    }

    public String getName(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference candidateDoc = db.collection("candidats").document(id);
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = candidateDoc.get();
        try {
            DocumentSnapshot document = documentSnapshotApiFuture.get();
            if (document.exists()) {
                // Récupérez le champ "name" du document
                String candidateName = document.getString("name");
                return candidateName;
            } else {
                return "Aucun candidat trouvé avec l'ID " + id;
            }
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    public boolean checkUser(String email) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference candidateDoc = db.collection("votes").document(email);
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = candidateDoc.get();
        try {
            DocumentSnapshot document = documentSnapshotApiFuture.get();
            if (document.exists()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
