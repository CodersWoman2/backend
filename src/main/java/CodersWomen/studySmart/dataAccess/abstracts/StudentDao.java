package CodersWomen.studySmart.dataAccess.abstracts;

import CodersWomen.studySmart.entities.concretes.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentDao extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
