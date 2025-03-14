package todos.app.todos.services.Users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import todos.app.todos.DTOs.Login.LoginDto;
import todos.app.todos.DTOs.Login.LoginRequest;
import todos.app.todos.DTOs.Register.UserRegisterDTO;
import todos.app.todos.entities.UserEntity;
import todos.app.todos.repositories.UserRepository;
import todos.app.todos.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public LoginDto login(LoginRequest loginRequest) {

        // Autentica usuario con Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserOrEmail(), loginRequest.getPassword())
        );

        UserEntity user = userRepository.findByEmailOrUserName(loginRequest.getUserOrEmail(), loginRequest.getUserOrEmail());

        if (user == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrectos");
        }

        // Genera token JWT
        String token = jwtUtil.generateToken(user.getUserName());

        // Devolver DTO con el token
        LoginDto loginDto = modelMapper.map(user, LoginDto.class);
        loginDto.setToken(token);

        return loginDto;
    }

    @Override
    public UserEntity register(UserRegisterDTO userRegisterDTO) {

        UserEntity user = userRepository.findByEmailOrUserName(userRegisterDTO.getEmail(), userRegisterDTO.getUserName());

        if (user != null) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT,
                    "Ya existe un usuario con ese email o nombre de usuario");
        }

        user = new UserEntity();
        user.setEmail(userRegisterDTO.getEmail());
        user.setName(userRegisterDTO.getName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setUserName(userRegisterDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword())); //Encripta contraseña

        return userRepository.save(user);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).get();
    }
}
