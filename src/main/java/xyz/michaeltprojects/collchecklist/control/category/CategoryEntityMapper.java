package xyz.michaeltprojects.collchecklist.control.category;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.persistence.category.CategoryEntity;

@Mapper(componentModel = "spring")
interface CategoryEntityMapper {

    CategoryEntity map(Category category);
    Category map(CategoryEntity category);

}
