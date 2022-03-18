package xyz.michaeltprojects.collchecklist.security.control;

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
public class User {

    private long id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(min = 6, max = 50)
    @Email
    private String email;

    private Set<Role> roles;

}
