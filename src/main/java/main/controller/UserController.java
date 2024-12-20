package main.controller;

import main.bean.User;
import main.dto.LoginResponse;
import main.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import main.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        LoginResponse response = new LoginResponse(loginRequest.getUsername(), token);
        return ResponseEntity.ok(response); // 返回令牌和用户名
    }



    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        // 从 SecurityContextHolder 获取当前认证用户的用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null) ? authentication.getName() : null;

        if (username == null) {
            return ResponseEntity.status(401).build(); // 如果未认证，则返回 401
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(404).build(); // 如果用户不存在，返回 404
        }

        return ResponseEntity.ok(user);
    }
}
