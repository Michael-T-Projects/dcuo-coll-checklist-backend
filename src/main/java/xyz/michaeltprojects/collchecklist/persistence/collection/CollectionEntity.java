package xyz.michaeltprojects.collchecklist.persistence.collection;

import lombok.*;
import xyz.michaeltprojects.collchecklist.persistence.category.CategoryEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "collections")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(nullable = false)
    private String reward;

    @Column
    private String location;

    @Column
    private String event;

    @Column
    private String episode;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.EAGER)
    @NotNull
    @NotEmpty
    private Collection<CollectionPartEntity> collectionParts;

}
