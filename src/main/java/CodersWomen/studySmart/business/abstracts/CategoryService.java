package CodersWomen.studySmart.business.abstracts;


import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Category;

import java.util.List;

public interface CategoryService {
    Result add(Category category);
    Result update(Category category);
    Result delete(Long categoryId);

    DataResult<Category> getById(Long categoryId);
    DataResult<List<Category>> getAll();
}

