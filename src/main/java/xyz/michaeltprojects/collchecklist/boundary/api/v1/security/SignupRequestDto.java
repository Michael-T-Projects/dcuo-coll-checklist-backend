package xyz.michaeltprojects.collchecklist.boundary.api.v1.security;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignupRequestDto {

    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Size(min = 6, max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

}
