package br.com.clevinacio.todolist.domain.repository;

import br.com.clevinacio.todolist.domain.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    public List<TaskModel> findByIdUser(UUID idUser);
}
