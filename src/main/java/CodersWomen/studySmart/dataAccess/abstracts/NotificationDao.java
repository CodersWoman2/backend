package CodersWomen.studySmart.dataAccess.abstracts;

import CodersWomen.studySmart.entities.concretes.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationDao extends JpaRepository<Notification, Long> {

    List<Notification> findByStudentId(Long studentId);

    List<Notification> findByType(String type);
}
