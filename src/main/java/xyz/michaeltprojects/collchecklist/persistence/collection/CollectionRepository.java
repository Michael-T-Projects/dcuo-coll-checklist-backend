package xyz.michaeltprojects.collchecklist.persistence.collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long> {

    Page<CollectionEntity> findByCategoryId(long categoryId, Pageable pageable);

    Page<CollectionEntity> findByNameContaining(String name, Pageable pageable);

    Page<CollectionEntity> findByCategoryIdAndNameContaining(long categoryId, String name, Pageable pageable);

}
