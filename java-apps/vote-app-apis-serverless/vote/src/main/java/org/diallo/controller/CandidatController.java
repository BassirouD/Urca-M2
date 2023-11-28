package org.diallo.controller;

import lombok.AllArgsConstructor;
import org.diallo.entities.Candidate;
import org.diallo.services.interfaces.ICandidatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/candidat")
public class CandidatController {
    private ICandidatService iCandidatService;

    @GetMapping("/all")
    public List<Candidate> candidateList() throws ExecutionException, InterruptedException {
        return iCandidatService.candidatList();
    }

    @PostMapping("/create")
    public Candidate createCandidat(@RequestBody Candidate candidate) {
        return iCandidatService.create(candidate);
    }

    @PutMapping("/update")
    public Candidate updateCandidat(@RequestBody Candidate candidate) {
        return iCandidatService.update(candidate);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteCustomer(@PathVariable String id) throws ExecutionException, InterruptedException {
        return iCandidatService.delete(id);
    }
}
