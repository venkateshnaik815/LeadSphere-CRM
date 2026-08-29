package com.leadsphere.crm.patterns;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Product(String name, BigDecimal price, LocalDate releaseDate, boolean discounted) {}
