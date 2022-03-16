package xyz.michaeltprojects.collchecklist.control.category;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

}
