package xyz.michaeltprojects.collchecklist.boundary.api.v1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.michaeltprojects.collchecklist.security.common.JwtUtils;
import xyz.michaeltprojects.collchecklist.security.config.UserDetailsImpl;
import xyz.michaeltprojects.collchecklist.security.control.UserService;
import xyz.michaeltprojects.collchecklist.shared.MessageResponseDto;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthResource {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService service;
    private final UserDtoMapper mapper;

    private final static String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @PostMapping(path = "/login", consumes = DEFAULT_MEDIA_TYPE, produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<JwtResponseDto> authenticateUser(@Valid @RequestBody final LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseDto(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                jwt
        ));
    }

    @PostMapping(path = "/signup", consumes = DEFAULT_MEDIA_TYPE, produces = DEFAULT_MEDIA_TYPE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody final SignupRequestDto signupRequest) {
        if (service.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDto(HttpStatus.BAD_REQUEST, "Email is already in use!"));
        }

        if (service.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDto(HttpStatus.BAD_REQUEST, "Username is already taken!"));
        }

        return ResponseEntity.ok(mapper.map(service.create(mapper.map(signupRequest), null)));
    }

}
