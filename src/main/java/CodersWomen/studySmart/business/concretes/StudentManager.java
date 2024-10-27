package CodersWomen.studySmart.business.concretes;

import CodersWomen.studySmart.business.abstracts.StudentService;
import CodersWomen.studySmart.core.utilities.results.*;
import CodersWomen.studySmart.dataAccess.abstracts.StudentDao;
import CodersWomen.studySmart.entities.concretes.Homework;
import CodersWomen.studySmart.entities.concretes.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentManager(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public DataResult<List<Student>> getAll() {
        return new SuccessDataResult<>(studentDao.findAll(), "All students retrieved successfully.");
    }

    @Override
    public DataResult<List<Student>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return new SuccessDataResult<>(studentDao.findAll(pageable).getContent(), "Paged students retrieved successfully.");
    }

    @Override
    public Result add(Student student) {
        studentDao.save(student);
        return new SuccessResult("Student added successfully.");
    }

    @Override
    public Result update(Student student) {
        return null;
    }

    @Override
    public DataResult<Student> getById(Long studentId) {
        return studentDao.findById(studentId)
                .<DataResult<Student>>map(student -> new SuccessDataResult<>(student, "Student retrieved successfully."))
                .orElseGet(() -> new ErrorDataResult<>("Student not found."));
    }

    @Override
    public Result delete(Long studentId) {
        if (studentDao.existsById(studentId)) {
            studentDao.deleteById(studentId);
            return new SuccessResult("Student deleted successfully.");
        } else {
            return new ErrorResult("Student not found.");
        }
    }
}
