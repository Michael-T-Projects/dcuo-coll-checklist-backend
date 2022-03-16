package xyz.michaeltprojects.collchecklist.boundary.api.v1.security;

import lombok.*;
import xyz.michaeltprojects.collchecklist.security.persistence.ERole;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto {

    private UUID id;

    private ERole name;

}
