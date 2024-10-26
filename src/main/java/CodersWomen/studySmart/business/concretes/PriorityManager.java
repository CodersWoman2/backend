package CodersWomen.studySmart.business.concretes;

import CodersWomen.studySmart.business.abstracts.PriorityService;
import CodersWomen.studySmart.core.utilities.results.*;
import CodersWomen.studySmart.dataAccess.abstracts.PriorityDao;
import CodersWomen.studySmart.entities.concretes.Homework;
import CodersWomen.studySmart.entities.concretes.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityManager implements PriorityService {

    private final PriorityDao priorityDao;

    @Autowired
    public PriorityManager(PriorityDao priorityDao) {
        this.priorityDao = priorityDao;
    }

    @Override
    public Result add(Priority priority) {
        priorityDao.save(priority);
        return new SuccessResult("Priority added successfully.");
    }

    @Override
    public Result update(Priority priority) {
        if (priorityDao.existsById(priority.getId())) {
            priorityDao.save(priority);
            return new SuccessResult("Priority updated successfully.");
        } else {
            return new ErrorResult("Priority not found.");
        }
    }

    @Override
    public Result delete(Long priorityId) {
        if (priorityDao.existsById(priorityId)) {
            priorityDao.deleteById(priorityId);
            return new SuccessResult("Priority deleted successfully.");
        } else {
            return new ErrorResult("Priority not found.");
        }
    }

    @Override
    public DataResult<Priority> getById(Long priorityId) {
        return priorityDao.findById(priorityId)
                .<DataResult<Priority>>map(priority -> new SuccessDataResult<>(priority, "Priority retrieved successfully."))
                .orElseGet(() -> new ErrorDataResult<>("Priority not found."));
    }

    @Override
    public DataResult<List<Priority>> getAll() {
        return new SuccessDataResult<>(priorityDao.findAll(), "All priorities retrieved successfully.");
    }
}
