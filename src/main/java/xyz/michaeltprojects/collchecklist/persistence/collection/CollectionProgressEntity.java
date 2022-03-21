package xyz.michaeltprojects.collchecklist.persistence.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.michaeltprojects.collchecklist.security.persistence.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "collection_progresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollectionProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private CollectionEntity collection;

    @OneToMany
    private Collection<CollectionPartEntity> completedCollectionParts;

}
