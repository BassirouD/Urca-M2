package org.diallo.services.interfaces;

import org.diallo.entities.ResultVote;
import org.diallo.entities.Statistique;
import org.diallo.entities.Vote;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IVoteService {
    Vote voter(Vote vote);

    List<Vote> voteList() throws ExecutionException, InterruptedException;

    List<ResultVote> RESULT_VOTE_LIST() throws ExecutionException, InterruptedException;

    Statistique STATISTIQUE();

}
