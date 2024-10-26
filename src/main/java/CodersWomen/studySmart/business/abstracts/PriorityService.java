package CodersWomen.studySmart.business.abstracts;

import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Priority;

import java.util.List;

public interface PriorityService {
    Result add(Priority priority);
    Result update(Priority priority);
    Result delete(Long priorityId);

    DataResult<Priority> getById(Long priorityId);
    DataResult<List<Priority>> getAll();
}
