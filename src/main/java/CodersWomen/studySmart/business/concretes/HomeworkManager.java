package CodersWomen.studySmart.business.concretes;

import CodersWomen.studySmart.business.abstracts.HomeworkService;
import CodersWomen.studySmart.core.utilities.results.*;
import CodersWomen.studySmart.dataAccess.abstracts.HomeworkDao;
import CodersWomen.studySmart.entities.concretes.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkManager implements HomeworkService {

    private final HomeworkDao homeworkDao;

    @Autowired
    public HomeworkManager(HomeworkDao homeworkDao) {
        this.homeworkDao = homeworkDao;
    }

    @Override
    public DataResult<List<Homework>> getAll() {
        return new SuccessDataResult<>(homeworkDao.findAll(), "All homeworks retrieved successfully.");
    }

    @Override
    public DataResult<List<Homework>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "dueDate");
        return new SuccessDataResult<>(homeworkDao.findAll(sort), "Homeworks retrieved and sorted by due date.");
    }

    @Override
    public DataResult<List<Homework>> getAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return new SuccessDataResult<>(homeworkDao.findAll(pageable).getContent(), "Paged homeworks retrieved successfully.");
    }

    @Override
    public Result add(Homework homework) {
        if (homework.getDueDate() == null) {
            return new ErrorResult("Due date is required for homework.");
        }
        homeworkDao.save(homework);
        return new SuccessResult("Homework added successfully.");
    }

    @Override
    public Result update(Homework homework) {
        if (homeworkDao.existsById(homework.getId())) {
            homeworkDao.save(homework);
            return new SuccessResult("Homework updated successfully.");
        } else {
            return new ErrorResult("Homework not found.");
        }
    }

    @Override
    public Result delete(Long homeworkId) {
        if (homeworkDao.existsById(homeworkId)) {
            homeworkDao.deleteById(homeworkId);
            return new SuccessResult("Homework deleted successfully.");
        } else {
            return new ErrorResult("Homework not found.");
        }
    }

    @Override
    public DataResult<Homework> getById(Long homeworkId) {
        return homeworkDao.findById(homeworkId)
                .<DataResult<Homework>>map(homework -> new SuccessDataResult<>(homework, "Homework retrieved successfully."))
                .orElseGet(() -> new ErrorDataResult<>("Homework not found."));
    }



    @Override
    public DataResult<List<Homework>> getHomeworksByStudentId(Long studentId) {
        return new SuccessDataResult<>(homeworkDao.findByStudentId(studentId), "Homeworks for student retrieved successfully.");
    }
}
