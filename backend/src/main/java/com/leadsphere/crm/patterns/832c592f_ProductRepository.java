package com.leadsphere.crm.patterns;

import com.iluwatar.monolithic.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
