package CodersWomen.studySmart.business.abstracts;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Reminder;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderService {
    Result add(Reminder reminder);
    Result update(Reminder reminder);
    Result delete(Long reminderId);

    DataResult<Reminder> getById(Long reminderId);
    DataResult<List<Reminder>> getAll();
    DataResult<List<Reminder>> getRemindersByHomeworkId(Long homeworkId);
    DataResult<List<Reminder>> getRemindersBeforeDate(LocalDateTime date);
}

