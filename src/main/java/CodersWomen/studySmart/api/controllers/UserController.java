package CodersWomen.studySmart.api.controllers;

import CodersWomen.studySmart.business.abstracts.UserService;
import CodersWomen.studySmart.core.utilities.results.DataResult;
import CodersWomen.studySmart.core.utilities.results.Result;
import CodersWomen.studySmart.entities.concretes.User;
import CodersWomen.studySmart.entities.dtos.UserLoginDTO;
import CodersWomen.studySmart.entities.dtos.UserRegistrationDTO;
import CodersWomen.studySmart.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<Result> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        Result result = userService.register(userRegistrationDTO);
        return result.isSuccess() ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    // Login user and generate JWT
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        DataResult<User> loginResult = userService.login(userLoginDTO);

        if (!loginResult.isSuccess()) {
            // Unsuccessful login, return error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResult);
        }

        // Successful login, generate JWT
        String jwt = jwtUtil.generateToken(loginResult.getData().getEmail());
        Map<String, String> response = new HashMap<>();
        response.put("jwt", jwt);
        response.put("message", "Login successful");

        return ResponseEntity.ok(response);
    }

    // Retrieve all users (JWT-protected)
    @GetMapping("/all")
    public ResponseEntity<DataResult<List<User>>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }
}
