package CodersWomen.studySmart.dataAccess.abstracts;

import CodersWomen.studySmart.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
