package xyz.michaeltprojects.collchecklist.boundary.api.v1.security;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {

    private long id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String username;

    @NotBlank
    @Size(min = 6, max = 50)
    @Email
    private String email;

    private Set<String> roles;

}
