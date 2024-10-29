package CodersWomen.studySmart.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRoleDto {
    private Long id;
    private String email;
    private String role;
}
