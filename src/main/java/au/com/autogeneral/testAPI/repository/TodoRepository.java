package au.com.autogeneral.testAPI.repository;

import au.com.autogeneral.testAPI.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {}
