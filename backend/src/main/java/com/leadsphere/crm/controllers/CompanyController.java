package com.leadsphere.crm.controllers;

import com.leadsphere.crm.models.Company;
import com.leadsphere.crm.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public Company createCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Company> getCompanyById(@PathVariable UUID id) {
        return companyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Company> updateCompany(@PathVariable UUID id, @RequestBody Company companyDetails) {
        return companyRepository.findById(id)
                .map(company -> {
                    company.setName(companyDetails.getName());
                    company.setIndustry(companyDetails.getIndustry());
                    company.setWebsite(companyDetails.getWebsite());
                    company.setAddress(companyDetails.getAddress());
                    Company updated = companyRepository.save(company);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCompany(@PathVariable UUID id) {
        return companyRepository.findById(id)
                .map(company -> {
                    companyRepository.delete(company);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
