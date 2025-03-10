package az.idrak.appv1.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.idrak.appv1.dto.EnumDTO;
import az.idrak.appv1.dto.LoginUserDTO;
import az.idrak.appv1.dto.RegisterUserDTO;
import az.idrak.appv1.entity.user.User;
import az.idrak.appv1.enums.Role;
import az.idrak.appv1.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(RegisterUserDTO input) {
        Set<Role> roles = new HashSet<>();
//        var userRole = roleRepository.findByName("USER")
//                .orElseThrow(() -> new IllegalStateException("Role user was not initialized"));

        for (EnumDTO role : input.getRoles()) {
            roles.add(Role.of(role.getId()));
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEnabled(false);
        user.setRoles(roles);

        return userRepository.save(user);
    }


    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
