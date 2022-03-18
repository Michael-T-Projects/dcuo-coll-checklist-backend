package xyz.michaeltprojects.collchecklist.boundary.api.v1.security;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.security.control.Role;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {

    RoleDto map(Role role);
    Role map(RoleDto role);

}
