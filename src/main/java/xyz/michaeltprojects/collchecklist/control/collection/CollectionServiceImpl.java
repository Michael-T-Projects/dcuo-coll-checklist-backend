package xyz.michaeltprojects.collchecklist.control.collection;

import lombok.RequiredArgsConstructor;
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
    public java.util.Collection<Collection> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public java.util.Collection<Collection> findByCategoryId(long categoryId) {
        return repository.findByCategoryId(categoryId).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public java.util.Collection<Collection> findByNameContaining(String name) {
        return repository.findByNameContaining(name).stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public java.util.Collection<Collection> findByCategoryIdAndNameContaining(long categoryId, String name) {
        return repository.findByCategoryIdAndNameContaining(categoryId, name).stream().map(mapper::map).collect(Collectors.toList());
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
    public CollectionProgress findByCollectionNameContaining(String collectionName) {
        return mapper.map(collectionProgressRepository.findByCollectionNameContaining(collectionName));
    }

    @Override
    public CollectionProgress update(CollectionProgress collectionProgress) {
        return mapper.map(collectionProgressRepository.save(mapper.map(collectionProgress)));
    }
}
