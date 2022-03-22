package xyz.michaeltprojects.collchecklist.boundary.api.v1.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserDto {

    private long id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String username;

    @NotBlank
    @Size(min = 120)
    @JsonIgnore
    private String password;

    @NotBlank
    @Size(min = 6, max = 50)
    @Email
    private String email;

    private Set<RoleDto> roles;

}
