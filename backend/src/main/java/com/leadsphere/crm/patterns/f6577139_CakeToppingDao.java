package com.leadsphere.crm.patterns;

import entity.CakeTopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeToppingDao extends JpaRepository<CakeTopping, Long> {}
