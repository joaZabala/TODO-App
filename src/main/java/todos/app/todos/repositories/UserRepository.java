package todos.app.todos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import todos.app.todos.entities.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmailOrUserName(String email, String userName);
}
