package xyz.michaeltprojects.collchecklist.boundary.api.v1.security;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthTokenDto {

    private String username;

    private String email;

    private String token;

}
