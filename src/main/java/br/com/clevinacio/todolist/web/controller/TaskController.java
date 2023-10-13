package br.com.clevinacio.todolist.web.controller;

import br.com.clevinacio.todolist.domain.model.TaskModel;
import br.com.clevinacio.todolist.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel){
        var createdTask = this.taskService.create(taskModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskModel);
    }
}
