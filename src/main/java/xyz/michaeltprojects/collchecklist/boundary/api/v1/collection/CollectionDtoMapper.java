package xyz.michaeltprojects.collchecklist.boundary.api.v1.collection;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.control.collection.Collection;
import xyz.michaeltprojects.collchecklist.control.collection.CollectionPart;
import xyz.michaeltprojects.collchecklist.control.collection.CollectionProgress;

@Mapper(componentModel = "spring")
public interface CollectionDtoMapper {

    CollectionDto map(Collection collection);
    Collection map(CollectionDto collection);
    CollectionPartDto map(CollectionPart collectionPart);
    CollectionPart map(CollectionPartDto collectionPart);
    CollectionProgressDto map(CollectionProgress collectionProgress);
    CollectionProgress map(CollectionProgressDto collectionProgress);

}
