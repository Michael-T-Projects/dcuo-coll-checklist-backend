package xyz.michaeltprojects.collchecklist.control.collection;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.michaeltprojects.collchecklist.persistence.collection.CollectionPartRepository;
import xyz.michaeltprojects.collchecklist.persistence.collection.CollectionProgressRepository;
import xyz.michaeltprojects.collchecklist.persistence.collection.CollectionRepository;

import java.util.stream.Collectors;

@Service("collectionService")
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository repository;
    private final CollectionPartRepository collectionPartRepository;
    private final CollectionEntityMapper mapper;
    private final CollectionProgressRepository collectionProgressRepository;

    @Override
    public Page<Collection> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(mapper::map);
    }

    @Override
    public Page<Collection> findByCategoryId(long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByCategoryId(categoryId, pageable).map(mapper::map);
    }

    @Override
    public Page<Collection> findByNameContaining(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByNameContaining(name, pageable).map(mapper::map);
    }

    @Override
    public Page<Collection> findByCategoryIdAndNameContaining(long categoryId, String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByCategoryIdAndNameContaining(categoryId, name, pageable).map(mapper::map);
    }

    @Override
    public Collection save(Collection collection) {
        collection.setId(repository.save(mapper.map(collection)).getId());
        return collection;
    }

    @Override
    public CollectionPart save(CollectionPart collectionPart) {
        collectionPart.setId(collectionPartRepository.save(mapper.map(collectionPart)).getId());
        return collectionPart;
    }

    @Override
    public CollectionProgress save(CollectionProgress collectionProgress) {
        collectionProgress.setId(collectionProgressRepository.save(mapper.map(collectionProgress)).getId());
        return collectionProgress;
    }

    @Override
    public java.util.Collection<CollectionProgress> findByUserIdAndCollectionNameContaining(long userId, String collectionName) {
        return collectionProgressRepository.findByUserIdAndCollectionNameContaining(userId, collectionName).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public java.util.Collection<CollectionProgress> findCollectionProgressesByUserId(long userid) {
        return collectionProgressRepository.findByUserId(userid).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public CollectionProgress update(CollectionProgress collectionProgress) {
        return mapper.map(collectionProgressRepository.save(mapper.map(collectionProgress)));
    }

    @Override
    public Collection findById(long id) {
        return mapper.map(repository.findById(id).orElse(null));
    }

    @Override
    public CollectionProgress findByUserIdAndCollectionId(long userId, long collectionId) {
        return mapper.map(collectionProgressRepository.findByUserIdAndCollectionId(userId, collectionId));
    }

    @Override
    public CollectionProgress findCollectionProgressById(long id) {
        return mapper.map(collectionProgressRepository.findById(id).orElse(null));
    }

    @Override
    public CollectionPart findCollectionPartById(long id) {
        return mapper.map(collectionPartRepository.findById(id).orElse(null));
    }

    @Override
    public boolean delete(long id) {
        if (!collectionProgressRepository.existsById(id)) {
            return false;
        }

        collectionProgressRepository.deleteById(id);

        return true;
    }
}
