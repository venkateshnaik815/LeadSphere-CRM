package com.leadsphere.crm.controllers;

import com.leadsphere.crm.models.Contact;
import com.leadsphere.crm.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {
    
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Contact> getContactById(@PathVariable UUID id) {
        return contactRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Contact> updateContact(@PathVariable UUID id, @RequestBody Contact contactDetails) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setFirstName(contactDetails.getFirstName());
                    contact.setLastName(contactDetails.getLastName());
                    contact.setEmail(contactDetails.getEmail());
                    contact.setPhone(contactDetails.getPhone());
                    contact.setJobTitle(contactDetails.getJobTitle());
                    contact.setCompany(contactDetails.getCompany());
                    Contact updated = contactRepository.save(contact);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteContact(@PathVariable UUID id) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contactRepository.delete(contact);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
