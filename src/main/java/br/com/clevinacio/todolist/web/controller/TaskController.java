package br.com.clevinacio.todolist.web.controller;

import br.com.clevinacio.todolist.domain.model.TaskModel;
import br.com.clevinacio.todolist.domain.service.TaskService;
import br.com.clevinacio.todolist.exception.AccessNotAuthorizedException;
import br.com.clevinacio.todolist.exception.TaskNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var currentDate = LocalDateTime.now();

        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início/data de término deve ser maior que a data atual");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor que a data de término");
        }
        var idUser = request.getAttribute("idUser");
        var createdTask = this.taskService.create(taskModel, (UUID) idUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskModel);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");

        List<TaskModel> taskListByUser;

        taskListByUser = this.taskService.getTaskListByUser( (UUID) idUser);

        return taskListByUser;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request){
        try{
            var taskUpdated = this.taskService.updateTask(taskModel, id, (UUID) request.getAttribute("idUser"));
            return ResponseEntity.ok().body(taskUpdated);
        }catch (AccessNotAuthorizedException exception){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não possui permissão para realizar essa ação");
        }catch (TaskNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
    }
}
