package xyz.michaeltprojects.collchecklist.persistence.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionPartRepository extends JpaRepository<CollectionPartEntity, Long> {
}
