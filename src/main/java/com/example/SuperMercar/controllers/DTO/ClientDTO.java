package com.example.SuperMercar.controllers.DTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
