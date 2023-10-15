package br.com.clevinacio.todolist.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Tarefa não encontrada")
public class TaskNotFoundException extends Exception{
}
