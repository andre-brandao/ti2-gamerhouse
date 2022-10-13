package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.PlaylistDAO;
import model.Playlist;
import spark.Request;
import spark.Response;


public class PlaylistService {

	private PlaylistDAO playlistDAO = new PlaylistDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_nome = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	
	public PlaylistService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Playlist(), FORM_ORDERBY_nome);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Playlist(), orderBy);
	}

	
	public void makeForm(int tipo, Playlist playlist, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umplaylist = "";
		if(tipo != FORM_INSERT) {
			umplaylist += "\t<table width=\"80%\" bgcolor=\"#F5EB36\" align=\"center\">";
			umplaylist += "\t\t<tr>";
			umplaylist += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/playlist/list/1\">Novo Jogo</a></b></font></td>";
			umplaylist += "\t\t</tr>";
			umplaylist += "\t</table>";
			umplaylist += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/playlist/";
			String name, nome, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Jogo";
				nome = "Fifa, Counter Strike, ...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + playlist.getID();
				name = "Atualizar Jogo (ID " + playlist.getID() + ")";
				nome = playlist.getnome();
				buttonLabel = "Atualizar";
			}
			umplaylist += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umplaylist += "\t<table width=\"80%\" bgcolor=\"#F5EB36\" align=\"center\">";
			umplaylist += "\t\t<tr>";
			umplaylist += "\t\t\t<td colspan=\"3\" align=\"left\" class=\"Inserir\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umplaylist += "\t\t</tr>";
			umplaylist += "\t\t<tr>";
			umplaylist += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umplaylist += "\t\t</tr>";
			umplaylist += "\t\t<tr>";
			umplaylist += "\t\t\t<td>&nbsp;Nome do Jogo: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ nome +"\"></td>";
			umplaylist += "\t\t\t<td>Preço: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ playlist.getPreco() +"\"></td>";
			umplaylist += "\t\t</tr>";
			umplaylist += "\t\t<tr>";
			umplaylist += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umplaylist += "\t\t</tr>";
			umplaylist += "\t</table>";
			umplaylist += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umplaylist += "\t<table width=\"80%\" bgcolor=\"#470592\" align=\"center\">";
			umplaylist += "\t\t<tr>";
			umplaylist += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b class=\"Detalhar\">&nbsp;&nbsp;&nbsp;Detalhes (ID " + playlist.getID() + ")</b></font></td>";
			umplaylist += "\t\t</tr>";
			umplaylist += "\t\t<tr>";
			umplaylist += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umplaylist += "\t\t</tr>";
			umplaylist += "\t\t<tr>";
			umplaylist += "\t\t\t<td class=\"Descricao\">&nbsp;Descrição: "+ playlist.getnome() +"</td>";
			umplaylist += "\t\t\t<td class=\"Preco\">Preco: "+ playlist.getPreco() +"</td>";
			umplaylist += "\t\t</tr>";
			umplaylist += "\t\t<tr>";
			umplaylist += "\t\t\t<td>&nbsp;</td>";
			umplaylist += "\t\t</tr>";
			umplaylist += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-playlist>", umplaylist);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#F5EB36\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b class=\"Playlist\">&nbsp;&nbsp;&nbsp;Playlist</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/playlist/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/playlist/list/" + FORM_ORDERBY_nome + "\"><b>Descrição</b></a></td>\n" +
        		"\t<td><a href=\"/playlist/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Playlist> playlists;
		if (orderBy == FORM_ORDERBY_ID) {                 	playlists = playlistDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_nome) {		playlists = playlistDAO.getOrderBynome();
		} else if (orderBy == FORM_ORDERBY_PRECO) {			playlists = playlistDAO.getOrderByPreco();
		} else {											playlists = playlistDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Playlist p : playlists) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getID() + "</td>\n" +
            		  "\t<td>" + p.getnome() + "</td>\n" +
            		  "\t<td>" + p.getPreco() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/playlist/" + p.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/playlist/update/" + p.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteplaylist('" + p.getID() + "', '" + p.getnome() + "', '" + p.getPreco() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-playlist>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		float preco = Float.parseFloat(request.queryParams("preco"));
		
		String resp = "";
		
		Playlist playlist = new Playlist(-1, nome, preco);
		
		if(playlistDAO.insert(playlist) == true) {
            resp = "Jogo (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Jogo (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Playlist playlist = (Playlist) playlistDAO.get(id);
		
		if (playlist != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, playlist, FORM_ORDERBY_nome);
        } else {
            response.status(404); // 404 Not found
            String resp = "Jogo " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Playlist playlist = (Playlist) playlistDAO.get(id);
		
		if (playlist != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, playlist, FORM_ORDERBY_nome);
        } else {
            response.status(404); // 404 Not found
            String resp = "Jogo " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Playlist playlist = playlistDAO.get(id);
        String resp = "";       

        if (playlist != null) {
        	playlist.setnome(request.queryParams("nome"));
        	playlist.setPreco(Float.parseFloat(request.queryParams("preco")));
        	playlistDAO.update(playlist);
        	response.status(200); // success
            resp = "Jogo (ID " + playlist.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jogo (ID \" + playlist.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Playlist playlist = playlistDAO.get(id);
        String resp = "";       

        if (playlist != null) {
            playlistDAO.delete(id);
            response.status(200); // success
            resp = "Jogo (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jogo (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}