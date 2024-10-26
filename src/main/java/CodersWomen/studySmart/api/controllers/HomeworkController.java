package CodersWomen.studySmart.api.controllers;

import CodersWomen.studySmart.business.abstracts.HomeworkService;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.ErrorDataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Homework;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/homeworks")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody Homework homework) {
        return ResponseEntity.ok(this.homeworkService.add(homework));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody Homework homework) {
        return ResponseEntity.ok(this.homeworkService.update(homework));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.homeworkService.delete(id));
    }

    @GetMapping("/getAll")
    public DataResult<List<Homework>> getAll() {
        return this.homeworkService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<Homework> getById(@PathVariable Long id) {
        return this.homeworkService.getById(id);
    }

    @GetMapping("/getByStudentId/{studentId}")
    public DataResult<List<Homework>> getHomeworksByStudentId(@PathVariable Long studentId) {
        return this.homeworkService.getHomeworksByStudentId(studentId);
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
