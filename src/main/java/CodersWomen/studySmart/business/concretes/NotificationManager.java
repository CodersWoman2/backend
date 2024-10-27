package CodersWomen.studySmart.business.concretes;

import CodersWomen.studySmart.business.abstracts.NotificationService;
import CodersWomen.studySmart.core.utilities.results.*;
import CodersWomen.studySmart.dataAccess.abstracts.NotificationDao;
import CodersWomen.studySmart.entities.concretes.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationManager implements NotificationService {

    private NotificationDao notificationDao;

    @Autowired
    public NotificationManager(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    @Override
    public Result add(Notification notification) {
        notificationDao.save(notification);
        return new SuccessResult("Notification added successfully.");
    }

    @Override
    public DataResult<List<Notification>> getNotificationsByStudentId(Long studentId) {
        List<Notification> notifications = notificationDao.findByStudentId(studentId);
        return new SuccessDataResult<>(notifications, "Notifications for student retrieved successfully.");
    }
}
