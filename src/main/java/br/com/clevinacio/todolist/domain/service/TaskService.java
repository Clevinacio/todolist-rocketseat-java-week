package br.com.clevinacio.todolist.domain.service;

import br.com.clevinacio.todolist.domain.model.TaskModel;
import br.com.clevinacio.todolist.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public TaskModel create(TaskModel taskModel){
        return this.taskRepository.save(taskModel);
    }
}
