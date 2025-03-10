package az.idrak.appv1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import az.idrak.appv1.dto.LoginUserDTO;
import az.idrak.appv1.dto.RegisterUserDTO;
import az.idrak.appv1.entity.user.Token;
import az.idrak.appv1.entity.user.User;
import az.idrak.appv1.repository.TokenRepository;
import az.idrak.appv1.responses.LoginResponse;
import az.idrak.appv1.service.user.AuthenticationService;
import az.idrak.appv1.service.user.JwtService;

import java.time.LocalDateTime;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    private final TokenRepository tokenRepository;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        LocalDateTime now = LocalDateTime.now();
        Token token = new Token();
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setMessage("Login success");

        //login olan zaman user id ni və tokeni UserToken cədvəlinə yazır
        token.setToken(jwtToken);
        token.setCreatedAt(now);
        token.setExpiredAt(now.plusMinutes(jwtService.getExpirationTime() / 60000));
//        userToken.setValidateAt();
//        userToken.setLastUsed(new Date());
        token.setUser(authenticatedUser);
        tokenRepository.save(token);
        return ResponseEntity.ok(loginResponse);
    }
}
