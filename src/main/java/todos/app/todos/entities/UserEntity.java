package todos.app.todos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    private String email;
    private String name;
    private String lastName;
    private String userName;

    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z]).{8,}$", message = "La contraseña debe contener al menos una mayúscula")
    private String password;

    //private String roles;

   /* @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<TaskEntity> tasks;*/
}
