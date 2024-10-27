package CodersWomen.studySmart.api.controllers;

import CodersWomen.studySmart.business.abstracts.ReminderService;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.ErrorDataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Reminder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody Reminder reminder) {
        return ResponseEntity.ok(this.reminderService.add(reminder));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody Reminder reminder) {
        return ResponseEntity.ok(this.reminderService.update(reminder));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.reminderService.delete(id));
    }

    @GetMapping("/getAll")
    public DataResult<List<Reminder>> getAll() {
        return this.reminderService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<Reminder> getById(@PathVariable Long id) {
        return this.reminderService.getById(id);
    }

    @GetMapping("/getByHomeworkId/{homeworkId}")
    public DataResult<List<Reminder>> getRemindersByHomeworkId(@PathVariable Long homeworkId) {
        return this.reminderService.getRemindersByHomeworkId(homeworkId);
    }

    @GetMapping("/getBeforeDate/{date}")
    public DataResult<List<Reminder>> getRemindersBeforeDate(@PathVariable LocalDateTime date) {
        return this.reminderService.getRemindersBeforeDate(date);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDataResult<>(validationErrors, "Validation errors");
    }
}
