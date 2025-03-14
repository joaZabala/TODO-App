package todos.app.todos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todos.app.todos.DTOs.Task.TaskDTO;
import todos.app.todos.DTOs.Task.TaskRequest;
import todos.app.todos.entities.TaskEntity;
import todos.app.todos.services.Tasks.TaskService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("")
    public ResponseEntity<TaskDTO>assignTask(@RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO>getTask(@PathVariable Long taskId){
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDTO>>getTasks(@RequestParam Long userId){
        return ResponseEntity.ok(taskService.getTaskByUser(userId));
    }

    @GetMapping("/byUserName")
    public ResponseEntity<List<TaskDTO>>getTasksByUserName(@RequestParam String userName){
        return ResponseEntity.ok(taskService.getTaskByUserName(userName));
    }
    @GetMapping("/dueDate")
    public ResponseEntity<List<TaskDTO>>getTasksByDueDate(@RequestParam LocalDate date){
        return ResponseEntity.ok(taskService.taskByDueDate(date));
    }

    @GetMapping("/dueDateBetween")
    public ResponseEntity<List<TaskDTO>>getTasksBetweenDates(@RequestParam LocalDate startsDate, @RequestParam LocalDate dueDate){
        return ResponseEntity.ok(taskService.taskByDueDateBetween(startsDate, dueDate));
    }

    @PutMapping("/update")
    public ResponseEntity<TaskDTO>updateTask(@RequestBody TaskRequest taskRequest , @RequestParam Long taskId){
        return ResponseEntity.ok(taskService.updateTask(taskRequest , taskId));
    }
}
