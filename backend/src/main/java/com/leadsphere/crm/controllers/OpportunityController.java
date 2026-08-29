package com.leadsphere.crm.controllers;

import com.leadsphere.crm.models.Opportunity;
import com.leadsphere.crm.repositories.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/opportunities")
public class OpportunityController {
    
    @Autowired
    private OpportunityRepository opportunityRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public Opportunity createOpportunity(@RequestBody Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Opportunity> getOpportunityById(@PathVariable UUID id) {
        return opportunityRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Opportunity> updateOpportunity(@PathVariable UUID id, @RequestBody Opportunity opportunityDetails) {
        return opportunityRepository.findById(id)
                .map(opportunity -> {
                    opportunity.setTitle(opportunityDetails.getTitle());
                    opportunity.setAmount(opportunityDetails.getAmount());
                    opportunity.setStage(opportunityDetails.getStage());
                    opportunity.setCloseDate(opportunityDetails.getCloseDate());
                    opportunity.setCompany(opportunityDetails.getCompany());
                    opportunity.setAssignedTo(opportunityDetails.getAssignedTo());
                    Opportunity updated = opportunityRepository.save(opportunity);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/stage")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Opportunity> updateOpportunityStage(@PathVariable UUID id, @RequestBody String stage) {
        return opportunityRepository.findById(id)
                .map(opportunity -> {
                    opportunity.setStage(stage.replace("\"", ""));
                    Opportunity updated = opportunityRepository.save(opportunity);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteOpportunity(@PathVariable UUID id) {
        return opportunityRepository.findById(id)
                .map(opportunity -> {
                    opportunityRepository.delete(opportunity);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
