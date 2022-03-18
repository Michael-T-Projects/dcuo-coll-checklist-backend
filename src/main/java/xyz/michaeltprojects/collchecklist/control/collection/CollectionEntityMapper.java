package xyz.michaeltprojects.collchecklist.control.collection;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.persistence.collection.CollectionEntity;

@Mapper(componentModel = "spring")
interface CollectionEntityMapper {

    CollectionEntity map(Collection collection);
    Collection map(CollectionEntity collection);

}
