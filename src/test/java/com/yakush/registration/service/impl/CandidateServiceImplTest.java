package com.yakush.registration.service.impl;

import com.yakush.registration.model.Candidate;
import com.yakush.registration.repository.CandidateRepository;
import com.yakush.registration.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CandidateServiceImplTest {

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;


    @Test
    void saveCandidateTest() {
        Candidate candidate = Candidate.builder()
                .id(1L)
                .name("test")
                .email("test@gmail.com")
                .build();

        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        when(candidateRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(candidate));

        candidateService.saveCandidate(candidate);

        Candidate savedCandidate = candidateRepository.findByEmail("test@gmail.com").orElse(null);

        assertNotNull(savedCandidate);
        assertEquals("test", savedCandidate.getName());
        assertEquals("test@gmail.com", savedCandidate.getEmail());

        verify(candidateRepository, times(1)).save(any(Candidate.class));
        verify(candidateRepository, times(1)).findByEmail("test@gmail.com");
    }

    @Test
    void findAllCandidatesTest() {
        Candidate candidate = Candidate.builder()
                .id(1L)
                .name("test")
                .email("test@gmail.com")
                .build();
        when(candidateRepository.findAll()).thenReturn(List.of(candidate));

        List<Candidate> candidates = candidateService.findAllCandidates();

        assertNotNull(candidates);
        assertEquals(1, candidates.size());
        assertEquals("test", candidates.get(0).getName());
        assertEquals("test@gmail.com", candidates.get(0).getEmail());

        verify(candidateRepository, times(1)).findAll();

    }
}