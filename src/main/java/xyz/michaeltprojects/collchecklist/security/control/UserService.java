package xyz.michaeltprojects.collchecklist.security.control;

public interface UserService {

    User save(User user);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findById(long id);

}
