package CodersWomen.studySmart.api.controllers;

import CodersWomen.studySmart.business.abstracts.StudentService;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.ErrorDataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Student;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(this.studentService.add(student));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(this.studentService.update(student));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.studentService.delete(id));
    }

    @GetMapping("/getAll")
    public DataResult<List<Student>> getAll() {
        return this.studentService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<Student> getById(@PathVariable Long id) {
        return this.studentService.getById(id);
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
