package CodersWomen.studySmart.api.controllers;

import CodersWomen.studySmart.business.abstracts.StatusService;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.ErrorDataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Status;
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
@RequestMapping("/api/statuses")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody Status status) {
        return ResponseEntity.ok(this.statusService.add(status));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody Status status) {
        return ResponseEntity.ok(this.statusService.update(status));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.statusService.delete(id));
    }

    @GetMapping("/getAll")
    public DataResult<List<Status>> getAll() {
        return this.statusService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<Status> getById(@PathVariable Long id) {
        return this.statusService.getById(id);
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
