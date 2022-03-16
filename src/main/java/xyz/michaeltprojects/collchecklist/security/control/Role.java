package xyz.michaeltprojects.collchecklist.security.control;

import lombok.*;
import xyz.michaeltprojects.collchecklist.security.persistence.ERole;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    private UUID id;

    private ERole name;

}
