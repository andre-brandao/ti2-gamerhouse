package model;

/** 
 * Classe Jogo, com seus construtores e metodos. 
 * 
 * @javadoc
 */ 
public class Jogo {
	private int id;
	private String nome;
	private float preco;	
	
	public Jogo() {
		id = -1;
		nome = "";
		preco = 0.00F;
	}

	public Jogo(int id, String nome, float preco) {
		setId(id);
		setNome(nome);
		setPreco(preco);

	}		
	
	/**
	* Método responsavel por retornar o ID.
	* 
	* @return id
	*/
	public int getID() {
		return id;
	}

	/**
	* Método responsavel por definir o ID.
	* 
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	* Método responsavel por retornar o nome.
	* 
	* @return nome
	*/
	public String getNome() {
		return nome;
	}
	
	/**
	* Método responsavel por definir o nome.
	* 
	*/
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	* Método responsavel por retornar o preco.
	* 
	* @return preco
	*/
	public float getPreco() {
		return preco;
	}

	/**
	* Método responsavel por definir o preco.
    * 
	*/
	public void setPreco(float preco) {
		this.preco = preco;
	}




	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "playlist: " + nome + "   Preço: R$" + preco;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Jogo) obj).getID());
	}	
}