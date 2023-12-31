package br.com.clevinacio.todolist.web.controller;

import br.com.clevinacio.todolist.domain.model.UserModel;
import br.com.clevinacio.todolist.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        var createdUser = userService.createUser(userModel);

        if(createdUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
