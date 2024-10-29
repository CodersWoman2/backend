package CodersWomen.studySmart.business.concretes;

import CodersWomen.studySmart.business.abstracts.UserService;
import CodersWomen.studySmart.core.utilities.results.*;
import CodersWomen.studySmart.dataAccess.abstracts.UserDao;
import CodersWomen.studySmart.entities.concretes.User;
import CodersWomen.studySmart.entities.dtos.UserLoginDTO;
import CodersWomen.studySmart.entities.dtos.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<>(this.userDao.findAll(), "Data Listelendi.");
    }

    @Override
    public Result register(UserRegistrationDTO userRegistrationDTO) {
        // Check if passwords match
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            return new ErrorResult("Passwords do not match.");
        }

        // Check if email already exists
        Optional<User> existingUser = userDao.findByEmail(userRegistrationDTO.getEmail());
        if (existingUser.isPresent()) {
            return new ErrorResult("Email is already in use.");
        }

        // Create new User entity
        User user = new User();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(hashPassword(userRegistrationDTO.getPassword())); // Hash password
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setDateOfBirth(userRegistrationDTO.getDateOfBirth());

        userDao.save(user); // Save user to the database
        return new SuccessResult("User registered successfully.");
    }

    @Override
    public DataResult<User> login(UserLoginDTO userLoginDTO) {
        Optional<User> user = userDao.findByEmail(userLoginDTO.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(hashPassword(userLoginDTO.getPassword()))) {
            return new SuccessDataResult<>(user.get(), "Login successful.");
        }
        return new ErrorDataResult<>("Invalid email or password.");
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
