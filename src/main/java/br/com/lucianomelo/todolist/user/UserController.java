package br.com.lucianomelo.todolist.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;



/**
 * Modificador
 * public
 * private
 * protected
 */

 @RestController
 @RequestMapping("/users")
public class UserController {

  @Autowired        //O Spring gerencia, instancia o IUsesRepository
    private IUserRepository userRepository; //Aqui chama a interface criada

    @PostMapping("/")
    public UserModel create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null) {
            System.out.println("Usuário já existe");
            return null;
        }

        var userCreated = this.userRepository.save(userModel);
        return userCreated;    //retorna o usuário que foi criado
    }
    
}
