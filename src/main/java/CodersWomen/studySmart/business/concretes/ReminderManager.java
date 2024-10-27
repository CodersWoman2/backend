package CodersWomen.studySmart.business.concretes;

import CodersWomen.studySmart.business.abstracts.ReminderService;
import CodersWomen.studySmart.core.utilities.results.*;
import CodersWomen.studySmart.dataAccess.abstracts.ReminderDao;
import CodersWomen.studySmart.entities.concretes.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderManager implements ReminderService {

    private final ReminderDao reminderDao;

    @Autowired
    public ReminderManager(ReminderDao reminderDao) {
        this.reminderDao = reminderDao;
    }

    @Override
    public Result add(Reminder reminder) {
        reminderDao.save(reminder);
        return new SuccessResult("Reminder added successfully.");
    }

    @Override
    public Result update(Reminder reminder) {
        if (reminderDao.existsById(reminder.getId())) {
            reminderDao.save(reminder);
            return new SuccessResult("Reminder updated successfully.");
        } else {
            return new ErrorResult("Reminder not found.");
        }
    }

    @Override
    public Result delete(Long reminderId) {
        if (reminderDao.existsById(reminderId)) {
            reminderDao.deleteById(reminderId);
            return new SuccessResult("Reminder deleted successfully.");
        } else {
            return new ErrorResult("Reminder not found.");
        }
    }

    @Override
    public DataResult<Reminder> getById(Long reminderId) {
        return reminderDao.findById(reminderId)
                .<DataResult<Reminder>>map(reminder -> new SuccessDataResult<>(reminder, "Reminder retrieved successfully."))
                .orElseGet(() -> new ErrorDataResult<>("Reminder not found."));
    }

    @Override
    public DataResult<List<Reminder>> getAll() {
        return new SuccessDataResult<>(reminderDao.findAll(), "All reminders retrieved successfully.");
    }

    @Override
    public DataResult<List<Reminder>> getRemindersByHomeworkId(Long homeworkId) {
        List<Reminder> reminders = reminderDao.findByHomeworkId(homeworkId);
        return new SuccessDataResult<>(reminders, "Reminders for the specified homework retrieved successfully.");
    }

    @Override
    public DataResult<List<Reminder>> getRemindersBeforeDate(LocalDateTime date) {
        List<Reminder> reminders = reminderDao.findByReminderTimeBefore(date);
        return new SuccessDataResult<>(reminders, "Reminders before the specified date retrieved successfully.");
    }
}
