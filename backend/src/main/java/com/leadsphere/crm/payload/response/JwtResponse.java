package com.leadsphere.crm.payload.response;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;

    public JwtResponse(String accessToken, UUID id, String email, String firstName, String lastName, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
}
