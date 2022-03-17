package xyz.michaeltprojects.collchecklist.security.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.michaeltprojects.collchecklist.security.persistence.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;
    private final RoleEntityMapper mapper;

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public Role findByName(String name) {
        return mapper.map(repository.findByName(name).orElse(null));
    }

    public Role save(Role role) {
        role.setId(repository.save(mapper.map(role)).getId());
        return role;
    }

}
