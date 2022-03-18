package xyz.michaeltprojects.collchecklist.boundary.api.v1.collection;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.control.collection.Collection;

@Mapper(componentModel = "spring")
public interface CollectionDtoMapper {

    CollectionDto map(Collection collection);
    Collection map(CollectionDto collection);

}
