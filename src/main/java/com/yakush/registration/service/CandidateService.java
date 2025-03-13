package com.yakush.registration.service;


import com.yakush.registration.model.Candidate;

import java.util.List;

public interface CandidateService {
    void saveCandidate(Candidate candidate);
    List<Candidate>  findAllCandidates();
}
