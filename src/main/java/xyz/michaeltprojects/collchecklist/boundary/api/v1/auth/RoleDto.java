package xyz.michaeltprojects.collchecklist.boundary.api.v1.auth;

import lombok.*;
import xyz.michaeltprojects.collchecklist.security.control.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto {

    private UUID id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    private Collection<User> users;

}
