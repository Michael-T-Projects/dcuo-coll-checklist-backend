package xyz.michaeltprojects.collchecklist.persistence.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionProgressRepository extends JpaRepository<CollectionProgressEntity, Long> {

    CollectionProgressEntity findByCollectionNameContaining(String collectionName);

}
