package xyz.michaeltprojects.collchecklist.persistence.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

    Collection<CollectionEntity> findByCategoryId(long categoryId);

    Collection<CollectionEntity> findByNameContaining(String name);

}
