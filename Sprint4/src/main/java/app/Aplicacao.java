package app;

import static spark.Spark.*;
import service.JogoService;
import service.UsuarioService;

/** 
 * Classe Aplicacao, responsável pelo request e response. 
 * 
 * @javadoc
 */ 
public class Aplicacao {
	
	private static JogoService jogoService = new JogoService();
	
	private static UsuarioService usuarioService = new UsuarioService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public/front-end");
        
        post("/jogo/insert", (request, response) -> jogoService.insert(request, response));

        get("/jogo/:id", (request, response) -> jogoService.get(request, response));
        
        get("/jogo/list/:orderby", (request, response) -> jogoService.getAll(request, response));

        get("/jogo/update/:id", (request, response) -> jogoService.getToUpdate(request, response));
        
        post("/jogo/update/:id", (request, response) -> jogoService.update(request, response));
           
        get("/jogo/delete/:id", (request, response) -> jogoService.delete(request, response));
        
        
        
        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));

        get("/usuario/:id", (request, response) -> usuarioService.get(request, response));
        
        get("/usuario/list/:orderby", (request, response) -> usuarioService.getAll(request, response));

        get("/usuario/update/:id", (request, response) -> usuarioService.getToUpdate(request, response));
        
        post("/usuario/update/:id", (request, response) -> usuarioService.update(request, response));
        
        get("/usuario/delete/:id", (request, response) -> jogoService.delete(request, response));
           
             
    }
}