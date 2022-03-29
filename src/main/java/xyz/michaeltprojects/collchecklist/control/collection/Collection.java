package xyz.michaeltprojects.collchecklist.control.collection;

import lombok.*;
import xyz.michaeltprojects.collchecklist.control.category.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Collection {

    private long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 2, max = 100)
    private String reward;

    private String location;

    private String event;

    private String episode;

    private Category category;

    @NotEmpty
    @NotNull
    private java.util.Collection<CollectionPart> collectionParts;

}
