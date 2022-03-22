package xyz.michaeltprojects.collchecklist.control.collection;

public interface CollectionService {

    java.util.Collection<Collection> findAll();

    java.util.Collection<Collection> findByCategoryId(long categoryId, int page, int size);

    java.util.Collection<Collection> findByNameContaining(String name, int page, int size);

    java.util.Collection<Collection> findByCategoryIdAndNameContaining(long categoryId, String name, int page, int size);

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
