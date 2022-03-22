package xyz.michaeltprojects.collchecklist.boundary.api.v1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xyz.michaeltprojects.collchecklist.security.control.Role;
import xyz.michaeltprojects.collchecklist.security.control.User;
import xyz.michaeltprojects.collchecklist.security.control.UserService;
import xyz.michaeltprojects.collchecklist.security.util.TokenProvider;
import xyz.michaeltprojects.collchecklist.shared.MessageResponseDto;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class AuthResource {

    private static final String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;
    private final UserService userService;
    private final UserDtoMapper mapper;

    @PostMapping(path = "/authenticate", consumes = DEFAULT_MEDIA_TYPE, produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> generateToken(@Valid @RequestBody final LoginRequestDto loginRequest) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        User user = userService.findByUsername(loginRequest.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(new AuthTokenDto(user.getUsername(), user.getEmail(), token, user.getRoles().stream().map(Role::getName).collect(Collectors.toList())));
    }

    @PostMapping(path = "/signup", consumes = DEFAULT_MEDIA_TYPE, produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> saveUser(@Valid @RequestBody final SignupRequestDto signupRequest) {
        if (userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponseDto(HttpStatus.BAD_REQUEST, "Username is already taken"));
        }

        if (userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponseDto(HttpStatus.BAD_REQUEST, "Email is already in use"));
        }

        return ResponseEntity.ok(mapper.map(userService.save(mapper.map(signupRequest))));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "user-ping", produces = DEFAULT_MEDIA_TYPE)
    public MessageResponseDto userPing() {
        return new MessageResponseDto(HttpStatus.OK, "OK");
    }

}
