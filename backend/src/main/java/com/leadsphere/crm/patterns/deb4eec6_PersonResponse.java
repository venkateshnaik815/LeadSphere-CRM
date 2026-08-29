package com.leadsphere.crm.patterns;

public record PersonResponse(
    Long id,
    String firstName,
    String lastName,
    int age,
    String phoneNumber,
    String email,
    Long categoryId,
    String categoryType) {}
