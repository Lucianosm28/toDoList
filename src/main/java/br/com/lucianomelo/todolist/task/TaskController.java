package br.com.lucianomelo.todolist.task;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController                //Controller
@RequestMapping("/tasks")      //Caminho da task

public class TaskController {

    @Autowired                  //Spring gerencia a instância do Repository
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel) {  //RequestBody para saber que virá do corpo da aplicação os dados
        System.out.println("Chegou no controller");
        var task = this.taskRepository.save(taskModel);
        return task;
    }


    
}
