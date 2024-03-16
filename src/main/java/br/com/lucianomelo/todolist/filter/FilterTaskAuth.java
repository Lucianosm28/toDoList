package br.com.lucianomelo.todolist.filter;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.lucianomelo.todolist.user.IUserRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component  // Para que o Spring gerencie essa classe
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
                var servletPath = request.getServletPath();
                System.out.println("PATH"+ servletPath);
                if (servletPath.startsWith("/tasks/")) { //Se o servletPath "rota" for igual a tasks aí faz a operação abaixo
        
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
        var user = this.userRepository.findByUsername(username);
        if (user == null) {                                     //Se o usuário for nulo, abaixo responde código 401
            response.sendError(401);
        } else {
            //Validar senha
            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());  // Vai verificar se a senha colocada é verdadeira, se está cadastrada(vai validar)
            if (passwordVerify.verified) {
                request.setAttribute("idUser", user.getId()); //Vai "setar" o atributo idUser com o valor do Id 
                filterChain.doFilter(request, response);
            } else {
                response.sendError(401);
            }
            //Segue viagem

        } 
        
        } else {
            filterChain.doFilter(request, response);
        }

    }       
} 



   

    
    

