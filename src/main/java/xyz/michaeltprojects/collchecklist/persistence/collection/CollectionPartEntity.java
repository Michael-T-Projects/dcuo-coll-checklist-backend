package xyz.michaeltprojects.collchecklist.persistence.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "collection_parts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollectionPartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(nullable = false)
    private String name;

}
