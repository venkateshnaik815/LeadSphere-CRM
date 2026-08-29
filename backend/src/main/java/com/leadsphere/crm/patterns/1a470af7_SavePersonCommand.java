package com.leadsphere.crm.patterns;

public record SavePersonCommand(
    String firstName,
    String lastName,
    int age,
    String phoneNumber,
    String email,
    String address,
    Long categoryId,
    String categoryType) {}
