package org.diallo.controller;

import lombok.AllArgsConstructor;
import org.diallo.entities.ResultVote;
import org.diallo.entities.Statistique;
import org.diallo.entities.Vote;
import org.diallo.services.interfaces.IVoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/vote")
public class VoteController {
    IVoteService iVoteService;

    @PostMapping("/voter")
    public Vote voter(@RequestBody Vote vote) {
        return iVoteService.voter(vote);
    }

    @GetMapping("/allVotes")
    public List<Vote> candidateList() throws ExecutionException, InterruptedException {
        return iVoteService.voteList();
    }

    @GetMapping("/resultsVote")
    public List<ResultVote> result() throws ExecutionException, InterruptedException {
        return iVoteService.RESULT_VOTE_LIST();
    }

    @GetMapping("/stats")
    public Statistique stats() {
        return iVoteService.STATISTIQUE();
    }
}
