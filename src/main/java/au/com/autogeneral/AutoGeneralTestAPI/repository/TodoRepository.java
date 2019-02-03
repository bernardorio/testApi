package au.com.autogeneral.AutoGeneralTestAPI.repository;

import au.com.autogeneral.AutoGeneralTestAPI.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {}
