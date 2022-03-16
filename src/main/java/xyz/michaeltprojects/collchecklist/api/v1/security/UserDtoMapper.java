package xyz.michaeltprojects.collchecklist.api.v1.security;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.security.control.User;

@Mapper(componentModel = "spring")
interface UserDtoMapper {

    UserDto map(User user);
    User map(UserDto user);
    User map(SignupRequestDto signupRequestDto);

}
