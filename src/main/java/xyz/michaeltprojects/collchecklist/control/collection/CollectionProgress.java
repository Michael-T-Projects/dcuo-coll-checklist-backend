package xyz.michaeltprojects.collchecklist.control.collection;

import lombok.*;
import xyz.michaeltprojects.collchecklist.security.control.User;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollectionProgress {

    private long id;

    @NotNull
    private User user;

    @NotNull
    private Collection collection;

    private java.util.Collection<CollectionPart> completedCollectionParts;

}
