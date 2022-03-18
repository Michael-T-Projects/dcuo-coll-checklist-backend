package xyz.michaeltprojects.collchecklist.security.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.michaeltprojects.collchecklist.security.persistence.RoleRepository;

import javax.annotation.PostConstruct;

@Service("roleService")
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleEntityMapper mapper;

    @PostConstruct
    void init() {
        if (!repository.existsByName("USER")) {
            repository.save(mapper.map(new Role(0, "USER")));
        }

        if (!repository.existsByName("ADMIN")) {
            repository.save(mapper.map(new Role(0, "ADMIN")));
        }
    }

    @Override
    public Role findByName(String name) {
        return mapper.map(repository.findByName(name));
    }

}
