package br.com.lucianomelo.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data     //Automaticamente coloca os Getters e Setters
          //@Getter se for querer apenas os Getters
          //@Setter se for querer apenas os Setters

@Entity(name = "tb_users")          
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID" )           //O JPA que vai gerenciar o id
    private UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
