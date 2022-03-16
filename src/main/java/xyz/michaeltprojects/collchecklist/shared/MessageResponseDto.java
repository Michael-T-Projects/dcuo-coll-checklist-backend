package xyz.michaeltprojects.collchecklist.shared;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageResponseDto {

    private HttpStatus status;

    private String message;

}
