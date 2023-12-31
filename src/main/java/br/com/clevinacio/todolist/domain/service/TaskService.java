package br.com.clevinacio.todolist.domain.service;

import br.com.clevinacio.todolist.domain.model.TaskModel;
import br.com.clevinacio.todolist.domain.repository.TaskRepository;
import br.com.clevinacio.todolist.exception.AccessNotAuthorizedException;
import br.com.clevinacio.todolist.exception.TaskNotFoundException;
import br.com.clevinacio.todolist.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public TaskModel create(TaskModel taskModel, UUID idUser){
        taskModel.setIdUser(idUser);
        return this.taskRepository.save(taskModel);
    }

    public List<TaskModel> getTaskListByUser(UUID idUser) {
        var taskList = this.taskRepository.findByIdUser(idUser);

        return this.taskRepository.findByIdUser(idUser);
    }

    public TaskModel updateTask(TaskModel taskModel, UUID id, UUID idUser) throws AccessNotAuthorizedException, TaskNotFoundException {
        var task = this.taskRepository.findById(id).orElse(null);

        if(task == null){
            throw new TaskNotFoundException();
        }

        if(!task.getIdUser().equals(idUser)){
            throw new AccessNotAuthorizedException();
        }

        Utils.copyNonNullProperties(taskModel, task);

        assert task != null;
        return this.taskRepository.save(task);
    }
}
