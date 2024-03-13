package br.com.lucianomelo.todolist.user;

import lombok.Data;

@Data     //Automaticamente coloca os Getters e Setters
          //@Getter se for querer apenas os Getters
          //@Setter se for querer apenas os Setters

public class UserModel {
    private String username;
    private String name;
    private String password;
}
