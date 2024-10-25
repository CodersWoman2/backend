package CodersWomen.studySmart.business.concretes;

import CodersWomen.studySmart.business.abstracts.UserService;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.core.utilities.results.SuccessDataResult;
import CodersWomen.studySmart.core.utilities.results.SuccessResult;
import CodersWomen.studySmart.dataAccess.abstracts.UserDao;
import CodersWomen.studySmart.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        super();
        this.userDao = userDao;
    }
    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<>(this.userDao.findAll(),"Data Listelendi.");
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı eklendi.");
    }
}
