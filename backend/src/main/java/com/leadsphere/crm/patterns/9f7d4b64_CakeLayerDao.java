package com.leadsphere.crm.patterns;

import entity.CakeLayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeLayerDao extends JpaRepository<CakeLayer, Long> {}
