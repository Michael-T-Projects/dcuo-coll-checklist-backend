package xyz.michaeltprojects.collchecklist.boundary.api.v1.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
public class CollectionSaveRequestDto {

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    @NotBlank
    @Size(min = 2, max = 30)
    private String reward;

    private String location;

    private String event;

    private String episode;

    @NotNull
    @JsonProperty("category_id")
    private Long categoryId;

    @NotEmpty
    @JsonProperty("collection_parts")
    private Collection<String> collectionParts;

}
