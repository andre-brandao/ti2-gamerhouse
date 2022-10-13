package model;


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
		setnome(nome);
		setPreco(preco);

	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

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