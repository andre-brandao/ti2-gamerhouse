package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;

/** 
 * Classe UsuarioService, responsavel por inserções HTML, e chamadas do UsuarioDAO.
 * 
 * @javadoc
 */ 
public class UsuarioService {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_Codigo = 1;
	private final int FORM_ORDERBY_login = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	/**
	* Método Construtor
	* 
	*/
	public UsuarioService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Usuario(), FORM_ORDERBY_login);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Usuario(), orderBy);
	}

	/**
	* Método responsavel pela disposição HTML e chamadas.
	* 
	*/
	public void makeForm(int tipo, Usuario usuario, int orderBy) {
		String loginArquivo = "form2.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(loginArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umusuario = "";
		if(tipo != FORM_INSERT) {
			umusuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umusuario += "\t\t<tr>";
			umusuario += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/usuario/list/1\">Novo Usuario</a></b></font></td>";
			umusuario += "\t\t</tr>";
			umusuario += "\t</table>";
			umusuario += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/usuario/";
			String name, login, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Registrar";
				login = "";
				buttonLabel = "Inscrever";
			} else {
				action += "update/" + usuario.getCodigo();
				name = "Atualizar Usuario (Codigo " + usuario.getCodigo() + ")";
				login = usuario.getLogin();
				buttonLabel = "Atualizar";
			}
			umusuario += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" codigo=\"form-add\">";
			umusuario += "\t<table width=\"80%\" bgcolor=\"#F5EB36\" align=\"center\">";
			umusuario += "\t\t<tr>";
			umusuario += "\t\t\t<td colspan=\"3\" align=\"left\" class=\"registrar\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umusuario += "\t\t</tr>";
			umusuario += "\t\t<tr>";
			umusuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umusuario += "\t\t</tr>";
			umusuario += "\t\t<tr>";
			umusuario += "\t\t\t<td>&nbsp;Username: <input class=\"input--register\" type=\"text\" name=\"login\" value=\""+ login +"\"></td>";
			umusuario += "\t\t\t<td>Email: <input class=\"input--register\" type=\"text\" name=\"email\" value=\""+ usuario.getEmail() +"\"></td>";
			umusuario += "\t\t</tr>";
			umusuario += "\t\t<tr>";
			umusuario += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umusuario += "\t\t</tr>";
			umusuario += "\t</table>";
			umusuario += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umusuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umusuario += "\t\t<tr>";
			umusuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar usuario (Codigo " + usuario.getCodigo() + ")</b></font></td>";
			umusuario += "\t\t</tr>";
			umusuario += "\t\t<tr>";
			umusuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umusuario += "\t\t</tr>";
			umusuario += "\t\t<tr>";
			umusuario += "\t\t\t<td>&nbsp;Descrição: "+ usuario.getLogin() +"</td>";
			umusuario += "\t\t\t<td>Preco: "+ usuario.getSenha() +"</td>";
			umusuario += "\t\t</tr>";
			umusuario += "\t\t<tr>";
			umusuario += "\t\t\t<td>&nbsp;</td>";
			umusuario += "\t\t</tr>";
			umusuario += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-usuario>", umusuario);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de usuarios</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_Codigo + "\"><b>Codigo</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_login + "\"><b>Descrição</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Usuario> usuarios;
		if (orderBy == FORM_ORDERBY_Codigo) {                 	usuarios = usuarioDAO.getOrderByCodigo();
		} else if (orderBy == FORM_ORDERBY_login) {		usuarios = usuarioDAO.getOrderByLogin();
		} 
	    else {											usuarios = usuarioDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Usuario p : usuarios) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getCodigo() + "</td>\n" +
            		  "\t<td>" + p.getLogin() + "</td>\n" +
            		  "\t<td>" + p.getSenha() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/" + p.getCodigo() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/update/" + p.getCodigo() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteusuario('" + p.getCodigo() + "', '" + p.getLogin() + "', '" + p.getSenha() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-usuario>", list);				
	}
	
	/**
	* Realiza a inserção do objeto no banco de dados.
	* 
	*/
	public Object insert(Request request, Response response) {
		String login = request.queryParams("login");
		String senha = request.queryParams("senha");
		String email = request.queryParams("email");

		
		String resp = "";
		
		Usuario usuario = new Usuario(-1, login, senha, email);
		
		if(usuarioDAO.insert(usuario) == true) {
            resp = "Cadastro realizado!";
            response.status(201); // 201 Created
		} else {
			resp = "Não foi possível cadastar!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");

	}

	/**
	* Chamada do método GET no Usuario.
	* 
	*/
	public Object get(Request request, Response response) {
		int codigo = Integer.parseInt(request.params(":id"));		
		Usuario usuario = (Usuario) usuarioDAO.get(codigo);
		
		if (usuario != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, usuario, FORM_ORDERBY_login);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuario " + codigo + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" codigo=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" codigo=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	/**
	* Chamada do método UPDATE no Usuario.
	* 
	*/
	public Object getToUpdate(Request request, Response response) {
		int codigo = Integer.parseInt(request.params(":id"));		
		Usuario usuario = (Usuario) usuarioDAO.get(codigo);
		
		if (usuario != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, usuario, FORM_ORDERBY_login);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuario " + codigo + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" codigo=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" codigo=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
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
	
	/**
	* Método responsavel pela atualização do Usuario.
	* 
	*/
	public Object update(Request request, Response response) {
        int codigo = Integer.parseInt(request.params(":id"));
		Usuario usuario = usuarioDAO.get(codigo);
        String resp = "";       

        if (usuario != null) {
        	usuario.setLogin(request.queryParams("login"));
        	usuario.setSenha(request.queryParams("senha"));
        	usuarioDAO.update(usuario);
        	response.status(200); // success
            resp = "Usuario (Codigo " + usuario.getCodigo() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuario (Codigo \" + usuario.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" codigo=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" codigo=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	/**
	* Método responsavel pela exclusao do Usuario.
	* 
	*/
	public Object delete(Request request, Response response) {
        int codigo = Integer.parseInt(request.params(":codigo"));
        Usuario usuario = usuarioDAO.get(codigo);
        String resp = "";       

        if (usuario != null) {
            usuarioDAO.delete(codigo);
            response.status(200); // success
            resp = "Usuario (" + codigo + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuario (" + codigo + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" codigo=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" codigo=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}