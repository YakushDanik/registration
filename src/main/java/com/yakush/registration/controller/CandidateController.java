package com.yakush.registration.controller;

import com.yakush.registration.model.Candidate;
import com.yakush.registration.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCandidate(@RequestBody Candidate candidate) {
        try {
            candidateService.saveCandidate(candidate);
            return ResponseEntity.ok("Candidate registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/candidates")
    public ResponseEntity<?> getAllCandidates() {
        try {
            return ResponseEntity.ok(candidateService.findAllCandidates());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
