package com.leadsphere.crm.config;

import com.leadsphere.crm.models.*;
import com.leadsphere.crm.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@leadsphere.com");
            admin.setPassword(encoder.encode("password123"));
            admin.setRoles(new java.util.HashSet<>(roleRepository.findAll()));
            userRepository.save(admin);
        }

        if (companyRepository.count() == 0) {
            Company c1 = new Company(); c1.setName("Apex Tech Solutions"); c1.setIndustry("IT Services"); c1.setWebsite("apextech.in");
            Company c2 = new Company(); c2.setName("Vanguard Retail"); c2.setIndustry("Retail"); c2.setWebsite("vanguardretail.in");
            Company c3 = new Company(); c3.setName("Nirvana Logistics"); c3.setIndustry("Logistics"); c3.setWebsite("nirvanalogistics.com");
            companyRepository.saveAll(List.of(c1, c2, c3));

            Contact ct1 = new Contact(); ct1.setFirstName("Rahul"); ct1.setLastName("Sharma"); ct1.setEmail("rahul.s@apextech.in"); ct1.setCompany(c1);
            Contact ct2 = new Contact(); ct2.setFirstName("Priya"); ct2.setLastName("Patel"); ct2.setEmail("priya@vanguardretail.in"); ct2.setCompany(c2);
            contactRepository.saveAll(List.of(ct1, ct2));

            Lead l1 = new Lead(); l1.setFirstName("Vikram"); l1.setLastName("Singh"); l1.setEmail("vikram.s@startup.in"); l1.setStatus("NEW"); l1.setSource("Website");
            Lead l2 = new Lead(); l2.setFirstName("Anjali"); l2.setLastName("Desai"); l2.setEmail("anjali@enterprise.com"); l2.setStatus("CONTACTED"); l2.setSource("Referral");
            Lead l3 = new Lead(); l3.setFirstName("Karthik"); l3.setLastName("Reddy"); l3.setEmail("karthik.r@techventures.co"); l3.setStatus("QUALIFIED"); l3.setSource("Conference");
            leadRepository.saveAll(List.of(l1, l2, l3));

            Opportunity o1 = new Opportunity(); o1.setTitle("Apex Cloud Migration"); o1.setAmount(new BigDecimal("250000")); o1.setStage("PROSPECTING"); o1.setCloseDate(LocalDate.now().plusDays(30)); o1.setCompany(c1);
            Opportunity o2 = new Opportunity(); o2.setTitle("Vanguard POS System Upgrade"); o2.setAmount(new BigDecimal("450000")); o2.setStage("PROPOSAL"); o2.setCloseDate(LocalDate.now().plusDays(15)); o2.setCompany(c2);
            Opportunity o3 = new Opportunity(); o3.setTitle("Nirvana Fleet Tracking"); o3.setAmount(new BigDecimal("1200000")); o3.setStage("NEGOTIATION"); o3.setCloseDate(LocalDate.now().plusDays(5)); o3.setCompany(c3);
            Opportunity o4 = new Opportunity(); o4.setTitle("Apex IT Support Annual Contract"); o4.setAmount(new BigDecimal("85000")); o4.setStage("QUALIFICATION"); o4.setCloseDate(LocalDate.now().plusDays(45)); o4.setCompany(c1);
            opportunityRepository.saveAll(List.of(o1, o2, o3, o4));
        }
    }
}
