import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO {

    public UserDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(User usuario) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO usuario (idUsuario, email, nome, biografia, senha) "
                    + "VALUES (" + usuario.getIdUsuario() + ", '" + usuario.getEmail() + "', '"
                    + usuario.getNome() + "', '" + usuario.getBiografia() + "', '" + usuario.getSenha() + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public User get(int id) {
        User usuario = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM produto WHERE id=" + id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                usuario = new User(rs.getInt("idUsuario"),
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("biografia"),
                        rs.getString("senha"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }

    public List<User> get() {
        return get("");
    }

    public List<User> getOrderByCodigo() {
        return get("idUsuario");
    }

    public List<User> getOrderByLogin() {
        return get("login");
    }

    public List<User> getOrderBySexo() {
        return get("sexo");
    }

    private List<User> get(String orderBy) {

        List<User> usuarios = new ArrayList<User>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM usuario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                User u = new User(rs.getInt("idUsuario"),
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("biografia"),
                        rs.getString("senha"));
                usuarios.add(u);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }

    public boolean update(User usuario) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE usuario SET email = '" + usuario.getEmail()
                    + "', nome = '" + usuario.getNome()
                    + "', biografia = '" + usuario.getBiografia()
                    + "', senha = '" + usuario.getSenha()
                    + "'"
                    + " WHERE idUsuario = " + usuario.getIdUsuario();
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean delete(int codigo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM usuario WHERE idUsuario = " + codigo;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean autenticar(String email, String senha) {
        boolean resp = false;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM usuario WHERE email LIKE '" + email + "' AND senha LIKE '" + senha + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            resp = rs.next();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resp;
    }
}