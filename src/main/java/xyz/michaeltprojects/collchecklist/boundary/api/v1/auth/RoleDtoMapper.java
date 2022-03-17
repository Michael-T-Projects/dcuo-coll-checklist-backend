package xyz.michaeltprojects.collchecklist.boundary.api.v1.auth;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.security.control.Role;

@Mapper(componentModel = "spring")
interface RoleDtoMapper {

    RoleDto map(Role role);
    Role map(RoleDto role);

}
