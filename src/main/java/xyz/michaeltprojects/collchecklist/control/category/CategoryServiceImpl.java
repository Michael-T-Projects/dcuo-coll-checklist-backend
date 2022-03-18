package xyz.michaeltprojects.collchecklist.control.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.michaeltprojects.collchecklist.persistence.category.CategoryRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryEntityMapper mapper;
    private final CategoryRepository repository;

    private final static List<String> CATEGORY_NAMES = Arrays.asList(
            "Basic Collections",
            "Alert and Raid Collections",
            "Metacollections",
            "Seasonal and Event Collections",
            "Episode Collections",
            "Special Collections"
    );

    @PostConstruct
    void init() {
        for (String categoryName : CATEGORY_NAMES) {
            if (!repository.existsByName(categoryName)) {
                repository.save(mapper.map(Category.builder().name(categoryName).build()));
            }
        }
    }

    @Override
    public Collection<Category> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

}
