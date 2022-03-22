package xyz.michaeltprojects.collchecklist.control.collection;

import org.springframework.data.domain.Page;

public interface CollectionService {

    Page<Collection> findAll(int page, int size);

    Page<Collection> findByCategoryId(long categoryId, int page, int size);

    Page<Collection> findByNameContaining(String name, int page, int size);

    Page<Collection> findByCategoryIdAndNameContaining(long categoryId, String name, int page, int size);

    Collection save(Collection collection);

    CollectionPart save(CollectionPart collectionPart);

    CollectionProgress save(CollectionProgress collectionProgress);

    java.util.Collection<CollectionProgress> findByUserIdAndCollectionNameContaining(long userId, String collectionName);

    CollectionProgress update(CollectionProgress collectionProgress);

    Collection findById(long id);

    CollectionProgress findByUserIdAndCollectionId(long userId, long collectionId);

    CollectionProgress findCollectionProgressById(long id);

    CollectionPart findCollectionPartById(long id);

    boolean delete(long id);

}
