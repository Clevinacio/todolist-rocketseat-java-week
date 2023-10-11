package br.com.clevinacio.todolist.web.controller;

import br.com.clevinacio.todolist.domain.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        return null;
    }

}
