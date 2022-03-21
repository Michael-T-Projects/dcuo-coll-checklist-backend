package xyz.michaeltprojects.collchecklist.persistence.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CollectionProgressRepository extends JpaRepository<CollectionProgressEntity, Long> {

    Collection<CollectionProgressEntity> findByUserIdAndCollectionNameContaining(long userId, String collectionName);

    CollectionProgressEntity findByUserIdAndCollectionId(long userId, long collectionId);

}
