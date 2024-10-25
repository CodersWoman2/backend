package CodersWomen.studySmart.entities.dtos;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String email;
    private String password;
    private String confirmPassword;

}
