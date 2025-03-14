package todos.app.todos.services.Tasks;

import org.springframework.stereotype.Service;
import todos.app.todos.DTOs.Task.TaskDTO;
import todos.app.todos.DTOs.Task.TaskRequest;
import todos.app.todos.entities.TaskEntity;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TaskService {

    TaskDTO createTask(TaskRequest taskRequest);

    List<TaskDTO> getTaskByUser(Long userId);
    List<TaskDTO> getTaskByUserName(String userName);

    TaskDTO getTaskById(Long userId);

    List<TaskDTO> taskByDueDate(LocalDate dueDate);
    List<TaskDTO> taskByDueDateBetween(LocalDate startDate , LocalDate dueDate);

    TaskDTO updateTask(TaskRequest taskRequest , Long taskId);

}
