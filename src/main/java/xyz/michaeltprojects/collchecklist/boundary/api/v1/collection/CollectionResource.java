package xyz.michaeltprojects.collchecklist.boundary.api.v1.collection;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.michaeltprojects.collchecklist.boundary.api.v1.category.CategoryDto;
import xyz.michaeltprojects.collchecklist.boundary.api.v1.category.CategoryDtoMapper;
import xyz.michaeltprojects.collchecklist.control.category.CategoryService;
import xyz.michaeltprojects.collchecklist.control.collection.CollectionService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/collections")
@RequiredArgsConstructor
public class CollectionResource {

    private final static String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    private final CollectionService service;
    private final CategoryService categoryService;
    private final CollectionDtoMapper mapper;
    private final CategoryDtoMapper categoryMapper;

    @GetMapping(produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> findByCategoryId(
            @Valid @RequestParam(name = "category_id", required = false) final Long categoryId,
            @Valid @RequestParam(name = "name", required = false) final String name
    ) {
        Collection<CollectionDto> collections;

        if (categoryId != null && name != null) {
            if (!categoryService.existsById(categoryId)) {
                return ResponseEntity.notFound().build();
            }

            collections = service.findByCategoryIdAndNameContaining(categoryId, name).stream().map(mapper::map).collect(Collectors.toList());
        } else if (categoryId != null) {
            if (!categoryService.existsById(categoryId)) {
                return ResponseEntity.notFound().build();
            }

            collections = service.findByCategoryId(categoryId).stream().map(mapper::map).collect(Collectors.toList());
        } else if (name != null) {
            collections = service.findByNameContaining(name).stream().map(mapper::map).collect(Collectors.toList());
        } else {
            collections = service.findAll().stream().map(mapper::map).collect(Collectors.toList());
        }

        return ResponseEntity.ok(collections);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = DEFAULT_MEDIA_TYPE, produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> save(@Valid @RequestBody final CollectionSaveRequestDto collectionSaveRequest) {
        CategoryDto category = categoryMapper.map(categoryService.findById(collectionSaveRequest.getCategoryId()));

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        Collection<CollectionPartDto> collectionParts = new ArrayList<>();

        for (String collectionPart : collectionSaveRequest.getCollectionParts()) {
            collectionParts.add(mapper.map(service.save(mapper.map(new CollectionPartDto(
                    null,
                    collectionPart
            )))));
        }

        CollectionDto mappedCollection = new CollectionDto(
                null,
                collectionSaveRequest.getName(),
                collectionSaveRequest.getReward(),
                collectionSaveRequest.getLocation(),
                collectionSaveRequest.getEvent(),
                collectionSaveRequest.getEpisode(),
                category,
                collectionParts
        );

        return ResponseEntity.ok(mapper.map(service.save(mapper.map(mappedCollection))));
    }

}
