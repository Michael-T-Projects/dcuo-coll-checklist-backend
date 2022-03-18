package xyz.michaeltprojects.collchecklist.control.collection;

public interface CollectionService {

    java.util.Collection<Collection> findAll();

    java.util.Collection<Collection> findByCategoryId(long categoryId);

    java.util.Collection<Collection> findByNameContaining(String name);

    java.util.Collection<Collection> findByCategoryIdAndNameContaining(long categoryId, String name);

    Collection save(Collection collection);

}
