package dao;

import model.Jogo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/** 
 * Classe JogoDAO, manipula o banco de dados. 
 * 
 * @javadoc
 */ 
public class JogoDAO extends DAO {	
	public JogoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	/**
     * Método responsavel por gerar sql e inserir.
     * 
     */
	public boolean insert(Jogo jogo) {
		boolean status = false;
		try {
			String sql = "INSERT INTO jogo (nome, preco) "
		               + "VALUES ('" + jogo.getNome() + "', "
		               + jogo.getPreco() + ");";
			System.out.println(sql);
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
     * Método responsavel por selecionar tudo de um usuario.
     * 
     */
	public Jogo get(int id) {
		Jogo jogo = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 jogo = new Jogo(rs.getInt("id"), rs.getString("nome"), (float)rs.getDouble("preco"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogo;
	}
	
	
	public List<Jogo> get() {
		return get("");
	}

	/**
     * Método responsavel por selecionar o jogo ordenado por ID.
     * 
     */
	public List<Jogo> getOrderByID() {
		return get("id");		
	}
	
	/**
     * Método responsavel por selecionar o jogo ordenado por nome.
     * 
     */
	public List<Jogo> getOrderBynome() {
		return get("nome");		
	}
	
	/**
     * Método responsavel por selecionar o jogo ordenado por preco.
     * 
     */
	public List<Jogo> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Jogo> get(String orderBy) {
		List<Jogo> jogos = new ArrayList<Jogo>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogo" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Jogo p = new Jogo(rs.getInt("id"), rs.getString("nome"), (float)rs.getDouble("preco"));
	            jogos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogos;
	}
	
	/**
     * Método responsavel por atualizar um jogo.
     * 
     */
	public boolean update(Jogo jogo) {
		boolean status = false;
		try {  
			String sql = "UPDATE jogo SET nome = '" + jogo.getNome() + "', "
					   + "preco = " + jogo.getPreco() + " " + "WHERE id =" + jogo.getID();
			System.out.println(sql);
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	/**
     * Método responsavel por deletar um jogo.
     * 
     */
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM jogo WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}