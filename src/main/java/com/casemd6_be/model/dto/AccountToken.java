package com.casemd6_be.model.dto;

import com.casemd6_be.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountToken {
    private long id;
    private String email;
    private Role role;
    private String token;
}
