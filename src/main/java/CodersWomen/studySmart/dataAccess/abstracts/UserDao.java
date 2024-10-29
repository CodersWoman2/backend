package CodersWomen.studySmart.dataAccess.abstracts;

import CodersWomen.studySmart.entities.concretes.User;
import CodersWomen.studySmart.entities.dtos.UserLoginDTO;
import CodersWomen.studySmart.entities.dtos.UserRegistrationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT new CodersWomen.studySmart.entities.dtos.UserRegistrationDTO(u.email, u.password, u.firstName, u.lastName, u.dateOfBirth) " +
            "FROM User u")
    List<UserRegistrationDTO> getUserRegistrationDTO();

    @Query("SELECT new CodersWomen.studySmart.entities.dtos.UserLoginDTO(u.email, u.password) FROM User u")
    List<UserLoginDTO> getUserLoginDTO();

}
