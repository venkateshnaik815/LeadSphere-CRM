package com.leadsphere.crm.patterns;

import com.iluwatar.monolithic.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
