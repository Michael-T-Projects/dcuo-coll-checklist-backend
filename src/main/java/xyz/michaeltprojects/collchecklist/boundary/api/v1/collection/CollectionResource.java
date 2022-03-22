package xyz.michaeltprojects.collchecklist.boundary.api.v1.collection;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/collections")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class CollectionResource {

    private final static String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    private final CollectionService service;
    private final CategoryService categoryService;
    private final CollectionDtoMapper mapper;
    private final CategoryDtoMapper categoryMapper;

    @GetMapping(produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> findByCategoryId(
            @Valid @RequestParam(name = "category_id", required = false) final Long categoryId,
            @Valid @RequestParam(name = "name", required = false) final String name,
            @Valid @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @Valid @RequestParam(name = "size", required = false, defaultValue = "10") final int size
    ) {
        Collection<CollectionDto> collections;
        int currentPage;
        int totalPages;
        long totalItems;

        if (categoryId != null && name != null) {
            if (!categoryService.existsById(categoryId)) {
                return ResponseEntity.notFound().build();
            }

            Page<xyz.michaeltprojects.collchecklist.control.collection.Collection> collectionPage = service.findByCategoryIdAndNameContaining(categoryId, name, page, size);
            collections = collectionPage.toList().stream().map(mapper::map).collect(Collectors.toList());
            currentPage = collectionPage.getNumber();
            totalPages = collectionPage.getTotalPages();
            totalItems = collectionPage.getTotalElements();
        } else if (categoryId != null) {
            if (!categoryService.existsById(categoryId)) {
                return ResponseEntity.notFound().build();
            }

            Page<xyz.michaeltprojects.collchecklist.control.collection.Collection> collectionPage = service.findByCategoryId(categoryId, page, size);
            collections = collectionPage.toList().stream().map(mapper::map).collect(Collectors.toList());
            currentPage = collectionPage.getNumber();
            totalPages = collectionPage.getTotalPages();
            totalItems = collectionPage.getTotalElements();
        } else if (name != null) {
            Page<xyz.michaeltprojects.collchecklist.control.collection.Collection> collectionPage = service.findByNameContaining(name, page, size);
            collections = collectionPage.toList().stream().map(mapper::map).collect(Collectors.toList());
            currentPage = collectionPage.getNumber();
            totalPages = collectionPage.getTotalPages();
            totalItems = collectionPage.getTotalElements();
        } else {
            Page<xyz.michaeltprojects.collchecklist.control.collection.Collection> collectionPage = service.findAll(page, size);
            collections = collectionPage.toList().stream().map(mapper::map).collect(Collectors.toList());
            currentPage = collectionPage.getNumber();
            totalPages = collectionPage.getTotalPages();
            totalItems = collectionPage.getTotalElements();
        }

        Map<String, Object> response = new HashMap<>();

        response.put("items", collections);
        response.put("current_page", currentPage);
        response.put("total_pages", totalPages);
        response.put("total_items", totalItems);

        return ResponseEntity.ok(response);
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
