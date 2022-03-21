package xyz.michaeltprojects.collchecklist.control.collection;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.persistence.collection.CollectionEntity;
import xyz.michaeltprojects.collchecklist.persistence.collection.CollectionPartEntity;
import xyz.michaeltprojects.collchecklist.persistence.collection.CollectionProgressEntity;

@Mapper(componentModel = "spring")
interface CollectionEntityMapper {

    CollectionEntity map(Collection collection);
    Collection map(CollectionEntity collection);
    CollectionPartEntity map(CollectionPart collectionPart);
    CollectionPart map(CollectionPartEntity collectionPart);
    CollectionProgressEntity map(CollectionProgress collectionProgress);
    CollectionProgress map(CollectionProgressEntity collectionProgress);

}
