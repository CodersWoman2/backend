package CodersWomen.studySmart.business.abstracts;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.User;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();
    Result add(User user);
}