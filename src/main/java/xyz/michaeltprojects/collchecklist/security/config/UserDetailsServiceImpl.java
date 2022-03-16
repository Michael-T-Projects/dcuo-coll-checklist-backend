package xyz.michaeltprojects.collchecklist.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.michaeltprojects.collchecklist.security.persistence.UserEntity;
import xyz.michaeltprojects.collchecklist.security.persistence.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found!"));

        return UserDetailsImpl.build(user);
    }

}
