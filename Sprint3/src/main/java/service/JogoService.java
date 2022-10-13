package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.JogoDAO;
import model.Jogo;
import spark.Request;
import spark.Response;


public class JogoService {

	private JogoDAO jogoDAO = new JogoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_nome = 2;
	private final int FORM_ORDERBY_PRECO = 3;
	
	
	public JogoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Jogo(), FORM_ORDERBY_nome);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Jogo(), orderBy);
	}

	
	public void makeForm(int tipo, Jogo jogo, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umjogo = "";
		if(tipo != FORM_INSERT) {
			umjogo += "\t<table width=\"80%\" bgcolor=\"#F5EB36\" align=\"center\">";
			umjogo += "\t\t<tr>";
			umjogo += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/jogo/list/1\">Novo Jogo</a></b></font></td>";
			umjogo += "\t\t</tr>";
			umjogo += "\t</table>";
			umjogo += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/jogo/";
			String name, nome, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Jogo";
				nome = "Fifa, Counter Strike, ...";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + jogo.getID();
				name = "Atualizar Jogo (ID " + jogo.getID() + ")";
				nome = jogo.getnome();
				buttonLabel = "Atualizar";
			}
			umjogo += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umjogo += "\t<table width=\"80%\" bgcolor=\"#F5EB36\" align=\"center\">";
			umjogo += "\t\t<tr>";
			umjogo += "\t\t\t<td colspan=\"3\" align=\"left\" class=\"Inserir\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umjogo += "\t\t</tr>";
			umjogo += "\t\t<tr>";
			umjogo += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umjogo += "\t\t</tr>";
			umjogo += "\t\t<tr>";
			umjogo += "\t\t\t<td>&nbsp;Nome do Jogo: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ nome +"\"></td>";
			umjogo += "\t\t\t<td>Preço: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ jogo.getPreco() +"\"></td>";
			umjogo += "\t\t</tr>";
			umjogo += "\t\t<tr>";
			umjogo += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umjogo += "\t\t</tr>";
			umjogo += "\t</table>";
			umjogo += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umjogo += "\t<table width=\"80%\" bgcolor=\"#470592\" align=\"center\">";
			umjogo += "\t\t<tr>";
			umjogo += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b class=\"Detalhar\">&nbsp;&nbsp;&nbsp;Detalhes (ID " + jogo.getID() + ")</b></font></td>";
			umjogo += "\t\t</tr>";
			umjogo += "\t\t<tr>";
			umjogo += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umjogo += "\t\t</tr>";
			umjogo += "\t\t<tr>";
			umjogo += "\t\t\t<td class=\"Descricao\">&nbsp;Descrição: "+ jogo.getnome() +"</td>";
			umjogo += "\t\t\t<td class=\"Preco\">Preco: "+ jogo.getPreco() +"</td>";
			umjogo += "\t\t</tr>";
			umjogo += "\t\t<tr>";
			umjogo += "\t\t\t<td>&nbsp;</td>";
			umjogo += "\t\t</tr>";
			umjogo += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-jogo>", umjogo);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#F5EB36\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b class=\"Jogo\">&nbsp;&nbsp;&nbsp;Jogo</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/jogo/list/" + FORM_ORDERBY_ID + "\"><b>ID</b></a></td>\n" +
        		"\t<td><a href=\"/jogo/list/" + FORM_ORDERBY_nome + "\"><b>Descrição</b></a></td>\n" +
        		"\t<td><a href=\"/jogo/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Jogo> jogos;
		if (orderBy == FORM_ORDERBY_ID) {                 	jogos = jogoDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_nome) {		jogos = jogoDAO.getOrderBynome();
		} else if (orderBy == FORM_ORDERBY_PRECO) {			jogos = jogoDAO.getOrderByPreco();
		} else {											jogos = jogoDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Jogo p : jogos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getID() + "</td>\n" +
            		  "\t<td>" + p.getnome() + "</td>\n" +
            		  "\t<td>" + p.getPreco() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/jogo/" + p.getID() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/jogo/update/" + p.getID() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeletejogo('" + p.getID() + "', '" + p.getnome() + "', '" + p.getPreco() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-jogo>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String nome = request.queryParams("nome");
		float preco = Float.parseFloat(request.queryParams("preco"));
		
		String resp = "";
		
		Jogo jogo = new Jogo(-1, nome, preco);
		
		if(jogoDAO.insert(jogo) == true) {
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
		Jogo jogo = (Jogo) jogoDAO.get(id);
		
		if (jogo != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, jogo, FORM_ORDERBY_nome);
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
		Jogo jogo = (Jogo) jogoDAO.get(id);
		
		if (jogo != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, jogo, FORM_ORDERBY_nome);
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
		Jogo jogo = jogoDAO.get(id);
        String resp = "";       

        if (jogo != null) {
        	jogo.setnome(request.queryParams("nome"));
        	jogo.setPreco(Float.parseFloat(request.queryParams("preco")));
        	jogoDAO.update(jogo);
        	response.status(200); // success
            resp = "Jogo (ID " + jogo.getID() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jogo (ID \" + jogo.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Jogo jogo = jogoDAO.get(id);
        String resp = "";       

        if (jogo != null) {
            jogoDAO.delete(id);
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