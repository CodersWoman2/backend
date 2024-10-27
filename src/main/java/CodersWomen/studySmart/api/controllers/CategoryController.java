package CodersWomen.studySmart.api.controllers;

import CodersWomen.studySmart.business.abstracts.CategoryService;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.ErrorDataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Category;
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
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(this.categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(this.categoryService.update(category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.categoryService.delete(id));
    }

    @GetMapping("/getAll")
    public DataResult<List<Category>> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<Category> getById(@PathVariable Long id) {
        return this.categoryService.getById(id);
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
