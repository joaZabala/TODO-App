package todos.app.todos.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todos.app.todos.DTOs.Login.LoginDto;
import todos.app.todos.DTOs.Login.LoginRequest;
import todos.app.todos.DTOs.Register.UserRegisterDTO;
import todos.app.todos.entities.UserEntity;
import todos.app.todos.services.Users.UserService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<LoginDto> login( @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        return ResponseEntity.ok(userService.register(userRegisterDTO));
    }
}
