package xyz.michaeltprojects.collchecklist.security.control;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    private long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

}
