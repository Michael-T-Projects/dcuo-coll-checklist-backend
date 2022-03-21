package xyz.michaeltprojects.collchecklist.boundary.api.v1.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollectionProgressSaveRequestDto {

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @NotNull
    @JsonProperty("collection_id")
    private Long collectionId;

}
