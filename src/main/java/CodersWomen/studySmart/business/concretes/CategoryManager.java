package CodersWomen.studySmart.business.concretes;

import CodersWomen.studySmart.business.abstracts.CategoryService;
import CodersWomen.studySmart.core.utilities.results.*;
import CodersWomen.studySmart.dataAccess.abstracts.CategoryDao;
import CodersWomen.studySmart.entities.concretes.Category;
import CodersWomen.studySmart.entities.concretes.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryManager(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Result add(Category category) {
        categoryDao.save(category);
        return new SuccessResult("Category added successfully.");
    }

    @Override
    public Result update(Category category) {
        if (categoryDao.existsById(category.getId())) {
            categoryDao.save(category);
            return new SuccessResult("Category updated successfully.");
        } else {
            return new ErrorResult("Category not found.");
        }
    }

    @Override
    public Result delete(Long categoryId) {
        if (categoryDao.existsById(categoryId)) {
            categoryDao.deleteById(categoryId);
            return new SuccessResult("Category deleted successfully.");
        } else {
            return new ErrorResult("Category not found.");
        }
    }

    @Override
    public DataResult<Category> getById(Long categoryId) {
        return categoryDao.findById(categoryId)
                .<DataResult<Category>>map(category -> new SuccessDataResult<>(category, "Category retrieved successfully."))
                .orElseGet(() -> new ErrorDataResult<>("Category not found."));
    }

    @Override
    public DataResult<List<Category>> getAll() {
        return new SuccessDataResult<>(categoryDao.findAll(), "All categories retrieved successfully.");
    }
}
