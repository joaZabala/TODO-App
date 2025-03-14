package todos.app.todos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import todos.app.todos.entities.TaskEntity;
import todos.app.todos.entities.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    List<TaskEntity>findByUserEntity(UserEntity userEntity);
    List<TaskEntity>findByUserEntity_UserName(String userName);

    List<TaskEntity>findByDueDate(LocalDate localDate);
    @Query("SELECT te FROM TaskEntity te " +
            "WHERE te.dueDate between :startDate and :dueDate" +
            " ORDER BY te.dueDate desc ")
    List<TaskEntity>findByDueDateBetween(@Param("startDate")LocalDate startDate , @Param("dueDate")LocalDate dueDate);
}
