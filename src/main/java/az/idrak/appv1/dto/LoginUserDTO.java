package az.idrak.appv1.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginUserDTO {

    private String email;

    private String password;
}
