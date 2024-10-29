package CodersWomen.studySmart.entities.concretes;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @NotBlank
    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;


    @Column(name = "username", unique = true)
    private String username;

    @NotBlank
    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    @NotBlank
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "role", nullable = false)
    private String role = "USER";  // Default role is USER
}
