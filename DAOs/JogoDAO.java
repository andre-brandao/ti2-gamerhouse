import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JogoDAO extends DAO {

    public JogoDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Jogo jogo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO jogos (idJogos, nome, desenvolvedora, dataLancamento) "
                    + "VALUES ("
                    + jogo.getIdJogos() + ", '"
                    + jogo.getNome() + "', '"
                    + jogo.getDesenvolvedora() + "', '"
                    + jogo.getDataLancamento() + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Jogo get(int id) {
        Jogo jogo = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM jogo WHERE id=" + id;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                jogo = new Jogo(rs.getInt("idJogos"),
                        rs.getString("nome"),
                        rs.getString("desnvolvedora"),
                        rs.getDate("dataLancamento"));
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

    public List<Jogo> getOrderByCodigo() {
        return get("idJogos");
    }

    public List<Jogo> getOrderByDesenvolvedora() {
        return get("desenvolvedora");
    }

    public List<Jogo> getOrderByDataLancamento() {
        return get("dataLancamento");
    }

    private List<Jogo> get(String orderBy) {

        List<Jogo> jogos = new ArrayList<Jogo>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM jogos" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Jogo j = new Jogo(rs.getInt("idJogos"),
                        rs.getString("nome"),
                        rs.getString("desnvolvedora"),
                        rs.getDate("dataLancamento"));
                jogos.add(j);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return jogos;
    }

    public List<Jogo> getDesenvolvedora(String dev) {
        List<Jogo> jogos = new ArrayList<Jogo>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM jogo WHERE jogo.desenvolvedora LIKE '" + dev + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Jogo j = new Jogo(rs.getInt("idJogos"),
                        rs.getString("nome"),
                        rs.getString("desnvolvedora"),
                        rs.getDate("dataLancamento"));
                jogos.add(j);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return jogos;
    }

    // int idJogos;
    // String nome;
    // String desenvolvedora;
    // Date dataLancamento;

    public boolean update(Jogo jogo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE jogo SET nome = '" + jogo.getNome()
                    + "', desenvolvedora = '" + jogo.getDesenvolvedora()
                    + "', dataLancamento = '" + jogo.getDataLancamento()
                    + "'"
                    + " WHERE idJogos = " + jogo.getIdJogos();
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
            String sql = "DELETE FROM jogo WHERE idJogos = " + codigo;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}