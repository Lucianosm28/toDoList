package br.com.lucianomelo.todolist.filter;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component  // Para que o Spring gerencie essa classe
public class FilterTaskAuth extends OncePerRequestFilter {


    @Override 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    

        //Pegar autenticação(usuário e senha)
        var authorization = request.getHeader("Authorization");

        var authEncoded = authorization.substring("Basic".length()).trim(); //Vai tirar o "Basic" do print e remover o espaço em branco

        byte[] authDecode = Base64.getDecoder().decode(authEncoded);        // Vai decodificar, converter o código para ano print aparecer o usuário e senha no console
        
        var authString = new String(authDecode);
        

        //["cristianocr7", "7777"]
        String[] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];

        System.out.println("Authorization");
        System.out.println(username);
        System.out.println(password);


        //Validar usuário

        //Validar senha

        //Segue viagem

        filterChain.doFilter(request, response);
    
    }
   

    }
    

