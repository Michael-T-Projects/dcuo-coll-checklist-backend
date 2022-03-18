package xyz.michaeltprojects.collchecklist.boundary.api.v1.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.michaeltprojects.collchecklist.boundary.api.v1.category.CategoryDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollectionDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    @NotBlank
    @Size(min = 2, max = 30)
    private String reward;

    private String location;

    private String event;

    private String episode;

    private CategoryDto category;

    @JsonProperty("collection_parts")
    @NotEmpty
    @NotNull
    private Collection<CollectionPartDto> collectionParts;

}
