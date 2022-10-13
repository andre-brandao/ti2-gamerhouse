package dao;

import model.Playlist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class PlaylistDAO extends DAO {	
	public PlaylistDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Playlist playlist) {
		boolean status = false;
		try {
			String sql = "INSERT INTO playlist (nome, preco) "
		               + "VALUES ('" + playlist.getnome() + "', "
		               + playlist.getPreco() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Playlist get(int id) {
		Playlist playlist = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM playlist WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 playlist = new Playlist(rs.getInt("id"), rs.getString("nome"), (float)rs.getDouble("preco"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return playlist;
	}
	
	
	public List<Playlist> get() {
		return get("");
	}

	
	public List<Playlist> getOrderByID() {
		return get("id");		
	}
	
	
	public List<Playlist> getOrderBynome() {
		return get("nome");		
	}
	
	
	public List<Playlist> getOrderByPreco() {
		return get("preco");		
	}
	
	
	private List<Playlist> get(String orderBy) {
		List<Playlist> playlists = new ArrayList<Playlist>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM playlist" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Playlist p = new Playlist(rs.getInt("id"), rs.getString("nome"), (float)rs.getDouble("preco"));
	            playlists.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return playlists;
	}
	
	
	public boolean update(Playlist playlist) {
		boolean status = false;
		try {  
			String sql = "UPDATE playlist SET nome = '" + playlist.getnome() + "', "
					   + "preco = " + playlist.getPreco() + " " + "WHERE id =" + playlist.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM playlist WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}