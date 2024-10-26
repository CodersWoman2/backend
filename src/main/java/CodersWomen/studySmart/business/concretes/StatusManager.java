package CodersWomen.studySmart.business.concretes;

import CodersWomen.studySmart.business.abstracts.StatusService;
import CodersWomen.studySmart.core.utilities.results.*;
import CodersWomen.studySmart.dataAccess.abstracts.StatusDao;
import CodersWomen.studySmart.entities.concretes.Priority;
import CodersWomen.studySmart.entities.concretes.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusManager implements StatusService {

    private final StatusDao statusDao;

    @Autowired
    public StatusManager(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public Result add(Status status) {
        statusDao.save(status);
        return new SuccessResult("Status added successfully.");
    }

    @Override
    public Result update(Status status) {
        if (statusDao.existsById(status.getId())) {
            statusDao.save(status);
            return new SuccessResult("Status updated successfully.");
        } else {
            return new ErrorResult("Status not found.");
        }
    }

    @Override
    public Result delete(Long statusId) {
        if (statusDao.existsById(statusId)) {
            statusDao.deleteById(statusId);
            return new SuccessResult("Status deleted successfully.");
        } else {
            return new ErrorResult("Status not found.");
        }
    }

    @Override
    public DataResult<Status> getById(Long statusId) {
        return statusDao.findById(statusId)
                .<DataResult<Status>>map(status -> new SuccessDataResult<>(status, "Status retrieved successfully."))
                .orElseGet(() -> new ErrorDataResult<>("Status not found."));
    }

    @Override
    public DataResult<List<Status>> getAll() {
        return new SuccessDataResult<>(statusDao.findAll(), "All statuses retrieved successfully.");
    }
}
