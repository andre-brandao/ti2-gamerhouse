package model;


public class Usuario {
	private int codigo;
	private String login;
	private String senha;
	private String email;
	
	public Usuario() {
		codigo = -1;
		login = "";
		senha = "" ;
		email = "" ;
	}

	public Usuario(int codigo, String login, String senha, String email) {
		setCodigo(codigo);
		setLogin(login);
		setSenha(senha);
		setEmail(email);

	}		
	
	public int getCodigo(){
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}




	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "usuario: " + login + "   senha: " + senha + "   email: " + email;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getCodigo() == ((Usuario) obj).getCodigo());
	}	
}

