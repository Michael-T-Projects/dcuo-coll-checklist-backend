package xyz.michaeltprojects.collchecklist.api.v1.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtResponseDto {

    @JsonProperty("user_id")
    private UUID userId;

    private String username;

    private String email;

    private List<String> roles;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private final String tokenType = "Bearer";

}
