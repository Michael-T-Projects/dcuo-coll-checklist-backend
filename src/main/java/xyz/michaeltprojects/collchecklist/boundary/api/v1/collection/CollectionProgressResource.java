package xyz.michaeltprojects.collchecklist.boundary.api.v1.collection;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import xyz.michaeltprojects.collchecklist.boundary.api.v1.security.UserDtoMapper;
import xyz.michaeltprojects.collchecklist.control.collection.CollectionService;
import xyz.michaeltprojects.collchecklist.security.control.User;
import xyz.michaeltprojects.collchecklist.security.control.UserService;
import xyz.michaeltprojects.collchecklist.shared.MessageResponseDto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/collection-progresses")
@RequiredArgsConstructor
public class CollectionProgressResource {

    private final static String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    private final CollectionService service;
    private final UserService userService;
    private final CollectionDtoMapper mapper;
    private final UserDtoMapper userMapper;

    @PostMapping(consumes = DEFAULT_MEDIA_TYPE, produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> save(@Valid @RequestBody final CollectionProgressSaveRequestDto collectionProgressSaveRequest) {
        User requestedUser = userService.findById(collectionProgressSaveRequest.getUserId());

        if (requestedUser == null) {
            return ResponseEntity.notFound().build();
        }

        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.findByUsername(currentUserDetails.getUsername());

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (currentUser.getId() != requestedUser.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        CollectionDto collection = mapper.map(service.findById(collectionProgressSaveRequest.getCollectionId()));

        if (collection == null) {
            return ResponseEntity.notFound().build();
        }

        if (service.findByUserIdAndCollectionId(currentUser.getId(), collection.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponseDto(HttpStatus.CONFLICT, "CollectionProgress with same user id and collection id already exists"));
        }

        CollectionProgressDto collectionProgress = mapper.map(service.save(mapper.map(new CollectionProgressDto(
                null,
                userMapper.map(currentUser),
                collection,
                new ArrayList<>()
        ))));

        return ResponseEntity.ok(collectionProgress);
    }

    @GetMapping(produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> findByCollectionNameContaining(
            @Valid @RequestParam(name = "collection_name") final String collectionName
    ) {
        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.findByUsername(currentUserDetails.getUsername());

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Collection<CollectionProgressDto> collectionProgress = service
                .findByUserIdAndCollectionNameContaining(currentUser.getId(), collectionName)
                .stream().map(mapper::map).collect(Collectors.toList());

        return ResponseEntity.ok(collectionProgress);
    }

    @GetMapping(path = "/{id}", produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> findById(
            @Valid @PathVariable final long id
    ) {
        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.findByUsername(currentUserDetails.getUsername());

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CollectionProgressDto collectionProgress = mapper.map(service.findCollectionProgressById(id));

        if (collectionProgress == null) {
            return ResponseEntity.notFound().build();
        }

        if (collectionProgress.getUser().getId() != currentUser.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(collectionProgress);
    }

    @DeleteMapping(path = "/{id}", produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> delete(@Valid @PathVariable(name = "id") final long id) {
        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.findByUsername(currentUserDetails.getUsername());

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CollectionProgressDto requestedCollectionProgress = mapper.map(service.findCollectionProgressById(id));

        if (requestedCollectionProgress == null) {
            return ResponseEntity.notFound().build();
        }

        if (requestedCollectionProgress.getUser().getId() != currentUser.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (!service.delete(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "/{id}", consumes = DEFAULT_MEDIA_TYPE, produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> update(
            @Valid @PathVariable(name = "id") final long id,
            @RequestBody final Collection<Long> collectionPartIds
    ) {
        UserDetails currentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.findByUsername(currentUserDetails.getUsername());

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CollectionProgressDto requestedCollectionProgress = mapper.map(service.findCollectionProgressById(id));

        if (requestedCollectionProgress == null) {
            return ResponseEntity.notFound().build();
        }

        if (requestedCollectionProgress.getUser().getId() != currentUser.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Collection<CollectionPartDto> collectionParts = new ArrayList<>();

        for (long collectionPartId : collectionPartIds) {
            CollectionPartDto collectionPart = mapper.map(service.findCollectionPartById(collectionPartId));

            if (collectionPart == null) {
                return ResponseEntity.notFound().build();
            }

            collectionParts.add(collectionPart);
        }

        requestedCollectionProgress.setCompletedCollectionParts(collectionParts);

        service.save(mapper.map(requestedCollectionProgress));

        return ResponseEntity.ok(requestedCollectionProgress);
    }
}
