package todos.app.todos.DTOs.Login;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private Long id;
    private String email;
    private String userName;
    private String token;

    /*
    private String name;
    private String lastName;*/

}
