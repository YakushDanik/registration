package com.yakush.registration.controller;

import com.yakush.registration.model.Candidate;
import com.yakush.registration.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateControllerTest {

    @Mock
    private CandidateService candidateService;

    @InjectMocks
    private CandidateController candidateController;

    @Test
    void registerCandidateTest_Success() {
        Candidate candidate = Candidate.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .build();

        doNothing().when(candidateService).saveCandidate(any(Candidate.class));

        ResponseEntity<?> response = candidateController.registerCandidate(candidate);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Candidate registered successfully", response.getBody());

        verify(candidateService, times(1)).saveCandidate(any(Candidate.class));
    }

    @Test
    void registerCandidateTest_Failure() {
        // Arrange
        Candidate candidate = Candidate.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .build();

        doThrow(new IllegalArgumentException("Invalid candidate")).when(candidateService).saveCandidate(any(Candidate.class));

        ResponseEntity<?> response = candidateController.registerCandidate(candidate);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue()); // Ожидаем статус 400 (Bad Request)
        assertNull(response.getBody());

        verify(candidateService, times(1)).saveCandidate(any(Candidate.class));
    }

    @Test
    void getAllCandidatesTest_Success() {
        Candidate candidate1 = Candidate.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .build();

        Candidate candidate2 = Candidate.builder()
                .id(2L)
                .name("Jane Doe")
                .email("jane.doe@example.com")
                .build();

        List<Candidate> candidates = List.of(candidate1, candidate2);

        when(candidateService.findAllCandidates()).thenReturn(candidates);

        ResponseEntity<?> response = candidateController.getAllCandidates();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(candidates, response.getBody());

        verify(candidateService, times(1)).findAllCandidates();
    }

    @Test
    void getAllCandidatesTest_Failure() {
        when(candidateService.findAllCandidates()).thenThrow(new IllegalArgumentException("No candidates found"));

        ResponseEntity<?> response = candidateController.getAllCandidates();

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(candidateService, times(1)).findAllCandidates();
    }
}