package br.com.lucianomelo.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 
 * ID
 * Usuário (ID_USUARIO)
 * Descrição
 * Título
 * Data de Início
 * Data de término
 * Prioridade
 */

@Data                       //Getters e Setters
@Entity(name = "tb_tasks")  //Entidade
public class TaskModel {
 
 @Id                        //Chave primária
 @GeneratedValue(generator = "UUID")  //Gerar automaticamente id
 private UUID id;
 private String description;

 @Column(length = 50)       //Limitar o campo "title" em 50 caracteres 
 private String title;
 private LocalDateTime startAt;
 private LocalDateTime endAt;
 private String priority;

 private UUID idUser;


 @CreationTimestamp       //Quando a task foi criada no banco de dados
 private LocalDateTime createdAt;


 public void setTitle(String title) throws Exception {
    if (title.length()> 50) {
        throw new Exception("O campo title deve ter no máximo 50 caracteres");
    }
    this.title = title;
}

}
