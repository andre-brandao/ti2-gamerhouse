public class User {
    private int idUsuario;
    private String email;
    private String nome;
    private String biografia;
    private String senha;

    User(){
        setIdUsuario(-1);
        setEmail("");
        setNome("");
        setBiografia("");
        setSenha("");
    }

    User(int idUsuario, String email, String nome, String biografia, String senha){
        setIdUsuario(idUsuario);
        setEmail(email);
        setNome(nome);
        setBiografia(biografia);
        setSenha(senha);
    }

    @Override
    public String toString() {
        return "{" +
            " idUsuario='" + getIdUsuario() + "'" +
            ", email='" + getEmail() + "'" +
            ", nome='" + getNome() + "'" +
            ", biografia='" + getBiografia() + "'" +
            ", senha='" + getSenha() + "'" +
            "}";
    }


    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBiografia() {
        return this.biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
