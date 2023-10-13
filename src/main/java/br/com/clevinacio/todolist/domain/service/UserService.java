package br.com.clevinacio.todolist.domain.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.clevinacio.todolist.domain.model.UserModel;
import br.com.clevinacio.todolist.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel createUser(UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){
            return null;
        }

        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordHashred);

        return this.userRepository.save(userModel);
    }

}
