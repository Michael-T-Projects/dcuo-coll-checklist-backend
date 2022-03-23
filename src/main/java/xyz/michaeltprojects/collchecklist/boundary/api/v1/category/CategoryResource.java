package xyz.michaeltprojects.collchecklist.boundary.api.v1.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.michaeltprojects.collchecklist.control.category.CategoryServiceImpl;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class CategoryResource {

    private final CategoryServiceImpl service;
    private final CategoryDtoMapper mapper;

    private final static String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @GetMapping(produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<Collection<CategoryDto>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::map).collect(Collectors.toList()));
    }

    @GetMapping(path = "/{id}", produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> findById(@Valid @PathVariable("id") final long id) {
        CategoryDto category = mapper.map(service.findById(id));

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category);
    }

}
