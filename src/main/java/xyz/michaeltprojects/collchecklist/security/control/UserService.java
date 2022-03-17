package xyz.michaeltprojects.collchecklist.security.control;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.michaeltprojects.collchecklist.security.persistence.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final static String DEFAULT_ROLE = "ROLE_USER";

    private final UserRepository repository;
    private final UserEntityMapper mapper;
    private final PasswordEncoder encoder;
    private final RoleService roleService;

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return mapper.map(repository.findByEmail(email).orElse(null));
    }

    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(List.of(roleService.findByName(DEFAULT_ROLE)));

        user.setId(repository.save(mapper.map(user)).getId());

        return user;
    }

}
