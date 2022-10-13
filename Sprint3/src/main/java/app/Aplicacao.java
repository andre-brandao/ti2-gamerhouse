package app;

import static spark.Spark.*;
import service.PlaylistService;
import service.UsuarioService;


public class Aplicacao {
	
	private static PlaylistService playlistService = new PlaylistService();
	
	private static UsuarioService usuarioService = new UsuarioService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public/front-end");
        
        post("/playlist/insert", (request, response) -> playlistService.insert(request, response));

        get("/playlist/:id", (request, response) -> playlistService.get(request, response));
        
        get("/playlist/list/:orderby", (request, response) -> playlistService.getAll(request, response));

        get("/playlist/update/:id", (request, response) -> playlistService.getToUpdate(request, response));
        
        post("/playlist/update/:id", (request, response) -> playlistService.update(request, response));
           
        get("/playlist/delete/:id", (request, response) -> playlistService.delete(request, response));
        
        
        
        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));

        get("/usuario/:id", (request, response) -> usuarioService.get(request, response));
        
        get("/usuario/list/:orderby", (request, response) -> usuarioService.getAll(request, response));

        get("/usuario/update/:id", (request, response) -> usuarioService.getToUpdate(request, response));
        
        post("/usuario/update/:id", (request, response) -> usuarioService.update(request, response));
        
        get("/usuario/delete/:id", (request, response) -> playlistService.delete(request, response));
           
             
    }
}