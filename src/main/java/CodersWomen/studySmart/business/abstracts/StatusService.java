package CodersWomen.studySmart.business.abstracts;

import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.Status;

import java.util.List;

public interface StatusService {
    Result add(Status status);
    Result update(Status status);
    Result delete(Long statusId);

    DataResult<Status> getById(Long statusId);
    DataResult<List<Status>> getAll();
}
