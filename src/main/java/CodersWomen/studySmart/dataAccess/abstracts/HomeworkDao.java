package CodersWomen.studySmart.dataAccess.abstracts;

import CodersWomen.studySmart.entities.concretes.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface HomeworkDao extends JpaRepository<Homework, Long> {

    List<Homework> findByStudentId(Long studentId);

    List<Homework> findByDueDateBefore(LocalDateTime date);

    List<Homework> findByCompleted(boolean completed);
}
