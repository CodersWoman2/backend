package CodersWomen.studySmart.dataAccess.abstracts;

import CodersWomen.studySmart.entities.concretes.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ReminderDao extends JpaRepository<Reminder, Long> {

    List<Reminder> findByReminderTimeBefore(LocalDateTime dateTime);

    List<Reminder> findByHomeworkId(Long homeworkId);
}
