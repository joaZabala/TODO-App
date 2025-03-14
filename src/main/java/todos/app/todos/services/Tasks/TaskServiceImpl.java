package todos.app.todos.services.Tasks;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todos.app.todos.DTOs.Task.TaskDTO;
import todos.app.todos.DTOs.Task.TaskRequest;
import todos.app.todos.DTOs.UserDTO;
import todos.app.todos.entities.TaskEntity;
import todos.app.todos.entities.UserEntity;
import todos.app.todos.repositories.TaskRepository;
import todos.app.todos.services.Users.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    @Transactional
    public TaskDTO createTask(TaskRequest taskRequest) {

        UserEntity user = userService.findById(taskRequest.getIdUser());

        if(user== null){
            throw new EntityNotFoundException("No existe el usuario con el id "+ taskRequest.getIdUser());
        }
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setDescription(taskRequest.getDescription());
        taskEntity.setStartDate(LocalDateTime.now());
        taskEntity.setPriority(taskRequest.getPriority());
        taskEntity.setDueDate(taskRequest.getDueDate());
        taskEntity.setUserEntity(user);

        taskRepository.save(taskEntity);

        TaskDTO taskDTO = modelMapper.map(taskEntity, TaskDTO.class);
        taskDTO.setUserDTO(modelMapper.map(taskEntity.getUserEntity() , UserDTO.class));
        return  taskDTO;
    }

    @Override
    public List<TaskDTO> getTaskByUser(Long userId) {
        UserEntity user = userService.findById(userId);
        return taskRepository.findByUserEntity(user)
                .stream()
                .map(te -> {
                    TaskDTO taskDTO = modelMapper.map(te , TaskDTO.class);
                    taskDTO.setUserDTO(modelMapper.map(te.getUserEntity(), UserDTO.class));
                    return taskDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTaskByUserName(String userName) {
        return taskRepository.findByUserEntity_UserName(userName)
                .stream()
                .map(te -> {
                    TaskDTO taskDTO = modelMapper.map(te, TaskDTO.class);
                    taskDTO.setUserDTO(modelMapper.map(te.getUserEntity() , UserDTO.class));
                    return taskDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long userId) {

       Optional<TaskEntity> taskEntity =  taskRepository.findById(userId);

       if(taskEntity.isPresent()){
           TaskDTO task = modelMapper.map( taskEntity.get(), TaskDTO.class);
           task.setUserDTO(modelMapper.map(taskEntity.get().getUserEntity(), UserDTO.class));

           return task;
       }
        throw new EntityNotFoundException("No existe la tarea con el id especificado");
    }

    @Override
    public List<TaskDTO> taskByDueDate(LocalDate dueDate) {
        return taskRepository.findByDueDate(dueDate)
                .stream()
                .map(task ->{
                   TaskDTO taskDTO = modelMapper.map(task , TaskDTO.class);
                   taskDTO.setUserDTO(modelMapper.map(task.getUserEntity() , UserDTO.class));
                   return taskDTO;

                }).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> taskByDueDateBetween(LocalDate startDate, LocalDate dueDate) {
        return taskRepository.findByDueDateBetween(startDate , dueDate)
                 .stream().map(task ->{
                     TaskDTO taskDTO = modelMapper.map(task , TaskDTO.class);
                     taskDTO.setUserDTO(modelMapper.map(task.getUserEntity() , UserDTO.class));
                     return taskDTO;
                 }).collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(TaskRequest taskRequest, Long taskId) {

        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(taskId);

        if(taskEntityOptional.isEmpty()){
            throw new EntityNotFoundException("No existe la tarea con el id especificado");
        }else{
            TaskEntity taskEntity = taskEntityOptional.get();
            taskEntity.setDescription(taskRequest.getDescription());
            taskEntity.setPriority(taskRequest.getPriority());
            taskEntity.setDueDate(taskRequest.getDueDate());
            taskEntity.setStartDate(LocalDateTime.now());
            taskEntity = taskRepository.save(taskEntity);

            TaskDTO taskDTO = modelMapper.map(taskEntity, TaskDTO.class);
            taskDTO.setUserDTO(modelMapper.map(taskEntity.getUserEntity() , UserDTO.class));
            return taskDTO;
        }
    }
}
