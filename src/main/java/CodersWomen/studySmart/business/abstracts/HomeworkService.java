package CodersWomen.studySmart.business.abstracts;

import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Homework;

import java.util.List;

public interface HomeworkService {
    Result add(Homework homework);
    Result update(Homework homework);
    Result delete(Long homeworkId);

    DataResult<List<Homework>> getAll();
    DataResult<List<Homework>> getAllSorted();
    DataResult<List<Homework>> getAll(int pageNo, int pageSize);
    DataResult<Homework> getById(Long homeworkId);
    DataResult<List<Homework>> getHomeworksByStudentId(Long studentId);
}
