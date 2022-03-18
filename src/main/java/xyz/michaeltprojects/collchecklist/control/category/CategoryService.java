package xyz.michaeltprojects.collchecklist.control.category;

import java.util.Collection;

public interface CategoryService {

    Collection<Category> findAll();
    boolean existsById(long id);
    Category findById(long id);

}
