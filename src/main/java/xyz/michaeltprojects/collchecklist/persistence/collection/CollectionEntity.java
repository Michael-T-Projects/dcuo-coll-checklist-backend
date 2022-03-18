package xyz.michaeltprojects.collchecklist.persistence.collection;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
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

}
