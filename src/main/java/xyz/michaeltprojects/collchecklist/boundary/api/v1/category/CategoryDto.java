package xyz.michaeltprojects.collchecklist.boundary.api.v1.category;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private UUID id;

    private String name;

}
