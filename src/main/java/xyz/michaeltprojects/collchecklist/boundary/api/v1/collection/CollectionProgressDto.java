package xyz.michaeltprojects.collchecklist.boundary.api.v1.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import xyz.michaeltprojects.collchecklist.boundary.api.v1.security.UserDto;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollectionProgressDto {

    private Long id;

    @NotNull
    private UserDto user;

    @NotNull
    private CollectionDto collection;

    @JsonProperty("completed_collection_parts")
    private Collection<CollectionPartDto> completedCollectionParts;

}
