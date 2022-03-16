package xyz.michaeltprojects.collchecklist.boundary.api.v1.category;

import org.mapstruct.Mapper;
import xyz.michaeltprojects.collchecklist.control.category.Category;

@Mapper(componentModel = "spring")
interface CategoryDtoMapper {

    CategoryDto map(Category category);
    Category map(CategoryDto category);

}
