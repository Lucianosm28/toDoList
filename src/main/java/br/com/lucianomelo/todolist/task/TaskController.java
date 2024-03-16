package br.com.lucianomelo.todolist.task;

import org.springframework.web.bind.annotation.RestController;

import br.com.lucianomelo.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.var;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController                //Controller
@RequestMapping("/tasks")      //Caminho da task

public class TaskController {

    @Autowired                  //Spring gerencia a instância do Repository
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {  //RequestBody para saber que virá do corpo da aplicação os dados
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        // 10/03/2024 - Current
        // 10/02/2024 - startAt
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("A data de início/ data de término deve ser maior do que a atual");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getStartAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("A data de início deve ser menor do que a data de término");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/")
    public List<TaskModel> list (HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    //http://localhost:8080/tasks/895122313-cahjgsask-8631234
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request ) {
           
           var task = this.taskRepository.findById(id).orElse(null);
           
           if (task == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Tarefa não encontrada");
        
        } 

           var idUser = request.getAttribute("idUser");

           if (!task.getIdUser().equals(idUser)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não tem permissão para alterar essa tarefa");
        }

            Utils.copyNonNullProperties(taskModel, task);
            
            var taskUpdated = this.taskRepository.save(task);
            return ResponseEntity.ok().body(taskUpdated);
    }
}
