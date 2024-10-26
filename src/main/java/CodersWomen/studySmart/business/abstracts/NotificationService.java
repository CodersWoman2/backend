package CodersWomen.studySmart.business.abstracts;

import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Notification;

import java.util.List;

public interface NotificationService {
    Result add(Notification notification);
    DataResult<List<Notification>> getNotificationsByStudentId(Long studentId);
}
