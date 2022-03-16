package xyz.michaeltprojects.collchecklist.security.control;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.michaeltprojects.collchecklist.security.persistence.ERole;
import xyz.michaeltprojects.collchecklist.security.persistence.RoleRepository;
import xyz.michaeltprojects.collchecklist.security.persistence.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityMapper mapper;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostConstruct
    void init() {
        if (!roleRepository.existsByName(ERole.ROLE_USER)) {
            roleRepository.save(mapper.map(Role.builder().name(ERole.ROLE_USER).build()));
        }

        if (!roleRepository.existsByName(ERole.ROLE_ADMIN)) {
            roleRepository.save(mapper.map(Role.builder().name(ERole.ROLE_ADMIN).build()));
        }
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User create(User user, Set<String> roleNames) {
        user.setPassword(encoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();

        if (roleNames == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .map(mapper::map)
                    .orElseThrow(() -> new RuntimeException("role is not found!"));

            roles.add(userRole);
        } else {
            for (String roleName : roleNames) {
                if ("admin".equals(roleName)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .map(mapper::map)
                            .orElseThrow(() -> new RuntimeException("role is not found!"));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .map(mapper::map)
                            .orElseThrow(() -> new RuntimeException("role is not found!"));
                    roles.add(userRole);
                }
            }
        }

        user.setRoles(roles);
        user.setId(userRepository.save(mapper.map(user)).getId());

        return user;
    }

}
