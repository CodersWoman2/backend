package CodersWomen.studySmart.business.abstracts;

import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Student;

import java.util.List;

public interface StudentService {
    Result add(Student student);
    Result update(Student student);
    Result delete(Long studentId);

    DataResult<List<Student>> getAll();
    DataResult<List<Student>> getAll(int pageNo, int pageSize);
    DataResult<Student> getById(Long studentId);
}
