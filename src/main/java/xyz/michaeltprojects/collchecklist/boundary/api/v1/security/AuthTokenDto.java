package xyz.michaeltprojects.collchecklist.boundary.api.v1.security;

import lombok.*;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthTokenDto {

    private long id;

    private String username;

    private String email;

    private String token;

    private Collection<String> roles;

}
