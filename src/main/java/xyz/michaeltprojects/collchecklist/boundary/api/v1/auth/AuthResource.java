package xyz.michaeltprojects.collchecklist.boundary.api.v1.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.michaeltprojects.collchecklist.security.control.UserService;
import xyz.michaeltprojects.collchecklist.shared.MessageResponseDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthResource {

    private final UserService service;
    private final UserDtoMapper mapper;

    private final static String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @PostMapping(path = "/signup", consumes = DEFAULT_MEDIA_TYPE, produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody final SignupRequestDto signupRequest) {
        if (service.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponseDto(HttpStatus.BAD_REQUEST,
                    "Email is already in use"));
        }

        if (service.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponseDto(HttpStatus.BAD_REQUEST,
                    "Username is already taken"));
        }

        UserDto registeredUser = mapper.map(service.save(mapper.map(new UserDto(null, signupRequest.getUsername(),
                signupRequest.getEmail(), signupRequest.getPassword(), true, false, null))));

        return ResponseEntity.ok(registeredUser);
    }

}
