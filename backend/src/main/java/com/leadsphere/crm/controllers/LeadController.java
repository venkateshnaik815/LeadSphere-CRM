package com.leadsphere.crm.controllers;

import com.leadsphere.crm.models.Lead;
import com.leadsphere.crm.repositories.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/leads")
public class LeadController {
    
    @Autowired
    private LeadRepository leadRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public Lead createLead(@RequestBody Lead lead) {
        return leadRepository.save(lead);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Lead> getLeadById(@PathVariable UUID id) {
        return leadRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Lead> updateLead(@PathVariable UUID id, @RequestBody Lead leadDetails) {
        return leadRepository.findById(id)
                .map(lead -> {
                    lead.setFirstName(leadDetails.getFirstName());
                    lead.setLastName(leadDetails.getLastName());
                    lead.setEmail(leadDetails.getEmail());
                    lead.setPhone(leadDetails.getPhone());
                    lead.setStatus(leadDetails.getStatus());
                    lead.setSource(leadDetails.getSource());
                    lead.setAssignedTo(leadDetails.getAssignedTo());
                    Lead updated = leadRepository.save(lead);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Lead> updateLeadStatus(@PathVariable UUID id, @RequestBody String status) {
        return leadRepository.findById(id)
                .map(lead -> {
                    lead.setStatus(status);
                    Lead updated = leadRepository.save(lead);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteLead(@PathVariable UUID id) {
        return leadRepository.findById(id)
                .map(lead -> {
                    leadRepository.delete(lead);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
