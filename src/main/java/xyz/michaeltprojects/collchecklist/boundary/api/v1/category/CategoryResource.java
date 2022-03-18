package xyz.michaeltprojects.collchecklist.boundary.api.v1.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.michaeltprojects.collchecklist.control.category.CategoryServiceImpl;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryServiceImpl service;
    private final CategoryDtoMapper mapper;

    private final static String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @GetMapping(produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<Collection<CategoryDto>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::map).collect(Collectors.toList()));
    }

}
