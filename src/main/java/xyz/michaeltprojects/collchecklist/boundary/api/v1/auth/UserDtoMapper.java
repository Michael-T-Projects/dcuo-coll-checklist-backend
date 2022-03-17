package xyz.michaeltprojects.collchecklist.boundary.api.v1.auth;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.security.control.User;

@Mapper(componentModel = "spring")
interface UserDtoMapper {

    UserDto map(User user);
    User map(UserDto user);

}
