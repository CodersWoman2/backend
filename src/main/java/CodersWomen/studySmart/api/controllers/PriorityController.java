package CodersWomen.studySmart.api.controllers;

import CodersWomen.studySmart.business.abstracts.PriorityService;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.ErrorDataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Priority;
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
@RequestMapping("/api/priorities")
public class PriorityController {

    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody Priority priority) {
        return ResponseEntity.ok(this.priorityService.add(priority));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody Priority priority) {
        return ResponseEntity.ok(this.priorityService.update(priority));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.priorityService.delete(id));
    }

    @GetMapping("/getAll")
    public DataResult<List<Priority>> getAll() {
        return this.priorityService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<Priority> getById(@PathVariable Long id) {
        return this.priorityService.getById(id);
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
