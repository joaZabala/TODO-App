package todos.app.todos.DTOs.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import todos.app.todos.DTOs.Priority;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    private Long idUser;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
}
