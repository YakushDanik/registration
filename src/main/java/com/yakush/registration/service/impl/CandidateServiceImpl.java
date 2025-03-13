package com.yakush.registration.service.impl;

import com.yakush.registration.model.Candidate;
import com.yakush.registration.repository.CandidateRepository;
import com.yakush.registration.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    @Override
    public void saveCandidate(Candidate candidate) {
        validateName(candidate.getName());
        validateEmail(candidate.getEmail());
        candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> findAllCandidates() {
        return candidateRepository.findAll();
    }

    private void validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format for " + email);
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("invalid name");
    }
}
