package todos.app.todos.DTOs.Register;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {

    private String email;
    private String name;
    private String lastName;
    private String userName;
    private String password;
}
