package xyz.michaeltprojects.collchecklist.api.v1.security;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequestDto {

    @NotBlank
    @Size(min = 6, max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

}
