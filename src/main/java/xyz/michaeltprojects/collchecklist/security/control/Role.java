package xyz.michaeltprojects.collchecklist.security.control;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    private UUID id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    private Collection<User> users;

}
