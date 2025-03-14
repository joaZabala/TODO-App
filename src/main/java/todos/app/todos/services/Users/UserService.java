package todos.app.todos.services.Users;

import org.springframework.stereotype.Service;
import todos.app.todos.DTOs.Login.LoginDto;
import todos.app.todos.DTOs.Login.LoginRequest;
import todos.app.todos.DTOs.Register.UserRegisterDTO;
import todos.app.todos.entities.UserEntity;
@Service
public interface UserService {

    LoginDto login(LoginRequest loginRequest);

    UserEntity register(UserRegisterDTO userRegisterDTO);

    UserEntity findById(Long id);
}
