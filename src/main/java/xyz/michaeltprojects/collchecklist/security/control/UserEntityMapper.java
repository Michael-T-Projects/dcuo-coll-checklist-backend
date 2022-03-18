package xyz.michaeltprojects.collchecklist.security.control;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.security.persistence.UserEntity;

@Mapper(componentModel = "spring")
interface UserEntityMapper {

    UserEntity map(User user);
    User map(UserEntity user);

}
