import java.sql.Date;

public class Jogo{
    int idJogos;
    String nome;
    String desenvolvedora;
    Date dataLancamento;

    Jogo(){
        setIdJogos(-1);
        setNome("");
        setDesenvolvedora("");
        setDataLancamento(null);
    }

    public Jogo(int idJogos, String nome, String desenvolvedora, Date dataLancamento) {
        setIdJogos(idJogos);
        setNome(nome);
        setDesenvolvedora(desenvolvedora);
        setDataLancamento(dataLancamento);
    }

    public int getIdJogos() {
        return this.idJogos;
    }

    public void setIdJogos(int idJogos) {
        this.idJogos = idJogos;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesenvolvedora() {
        return this.desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public Date getDataLancamento() {
        return this.dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }


}