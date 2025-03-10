package az.idrak.appv1.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterUserDTO {

    private String email;

    private String password;

    private String fullName;

    private Set<EnumDTO> roles;
}
