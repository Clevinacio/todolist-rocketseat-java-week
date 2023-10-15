package br.com.clevinacio.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Usuário não possui permissão para realizar essa ação")
public class AccessNotAuthorizedException extends Exception {

}
