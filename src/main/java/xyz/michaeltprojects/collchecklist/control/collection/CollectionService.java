package xyz.michaeltprojects.collchecklist.control.collection;

public interface CollectionService {

    java.util.Collection<Collection> findByCategoryId(long categoryId);

    java.util.Collection<Collection> findByNameContaining(String name);

    Collection save(Collection collection);

}
