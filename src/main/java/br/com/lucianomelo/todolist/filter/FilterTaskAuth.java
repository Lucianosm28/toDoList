package br.com.lucianomelo.todolist.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component  // Para que o Spring gerencie essa classe
public class FilterTaskAuth implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        //Executar alguma ação
        System.out.println("Chegou no filtro");
        chain.doFilter(request, response); //Na requisição aqui determina se pode seguir adiante
    }
    
}
