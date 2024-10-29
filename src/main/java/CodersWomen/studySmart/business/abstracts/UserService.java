package CodersWomen.studySmart.business.abstracts;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.User;
import CodersWomen.studySmart.entities.dtos.UserLoginDTO;
import CodersWomen.studySmart.entities.dtos.UserRegistrationDTO;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();
    Result register(UserRegistrationDTO userRegistrationDTO);
    DataResult<User> login(UserLoginDTO userLoginDTO);

}