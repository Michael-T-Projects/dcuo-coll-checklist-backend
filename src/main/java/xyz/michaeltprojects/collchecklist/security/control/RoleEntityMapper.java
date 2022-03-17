package xyz.michaeltprojects.collchecklist.security.control;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.security.persistence.RoleEntity;

@Mapper(componentModel = "spring")
interface RoleEntityMapper {

    RoleEntity map(Role role);
    Role map(RoleEntity role);

}
