package com.leadsphere.crm.patterns;

import entity.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeDao extends JpaRepository<Cake, Long> {}
