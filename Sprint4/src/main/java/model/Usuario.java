package model;

/** 
 * Classe Usuario, com seus construtores e metodos. 
 * 
 * @javadoc
 */ 
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
	
	/**
	    * Método responsavel por retornar o codigo.
	    * 
	    * @return codigo
	    */
	public int getCodigo(){
		return codigo;
	}

	/**
	    * Método responsavel por definir o codigo.
	    * 
	    */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
     * Método responsavel por retornar o login.
     * 
     * @return login
     */
	public String getLogin() {
		return login;
	}

	/**
     * Método responsavel por definir o login.
     * 
     */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
     * Método responsavel por retornar a senha.
     * 
     * @return senha
     */
	public String getSenha() {
		return senha;
	}

	/**
     * Método responsavel por definir a senha.
     * 
     */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
     * Método responsavel por retornar o email.
     * 
     * @return email
     */
	public String getEmail() {
		return email;
	}

	/**
     * Método responsavel por definir o email.
     * 
     */
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

