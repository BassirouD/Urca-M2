package org.diallo.services.interfaces;

import org.diallo.entities.Candidate;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ICandidatService {
    Candidate create(Candidate candidate);

    Candidate update(Candidate candidate);

    List<Candidate> candidatList() throws ExecutionException, InterruptedException;

    Boolean delete(String id);
}
