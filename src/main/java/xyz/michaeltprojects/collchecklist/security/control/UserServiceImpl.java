package xyz.michaeltprojects.collchecklist.security.control;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.michaeltprojects.collchecklist.security.persistence.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final RoleService roleService;
    private final UserRepository repository;
    private final UserEntityMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.map(repository.findByUsername(username));

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return authorities;
    }

    @Override
    public User findByUsername(String username) {
        return mapper.map(repository.findByUsername(username));
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roles = new HashSet<>(List.of(role));

        user.setRoles(roles);
        user.setId(repository.save(mapper.map(user)).getId());

        return user;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User findById(long id) {
        return mapper.map(repository.findById(id).orElse(null));
    }

}
