package FAG.Objetos;

public class Produto {

	
	private String nome;
	private double preco;
	private int qtdEstoque;
		
	
	public Produto() {
	}

	
	public Produto(String nome, double preco, int qtdEstoque) {
		
		setNome(nome);
		setPreco(preco);
		setQtdEstoque(qtdEstoque);
		
	}
	
	
	

	public void setNome(String nome) {
		
		if(nome != null && !nome.isBlank()) {
		
			this.nome = nome;
		}
	}



	public void setPreco(double preco) {
		
		if(preco > 0) {
			this.preco = preco;
		}
	}


	
	public void setQtdEstoque(int qtdEstoque) {
		
		if(qtdEstoque >= 0) {
			this.qtdEstoque = qtdEstoque;
		}
	}

	
	public String getNome() {
		return nome;
	}


	public double getPreco() {
		return preco;
	}


	public int getQtdEstoque() {
		return qtdEstoque;
	}


	
	public void produtoInfo() {
		System.out.printf("%s valor: %.2f qtd estoque: %d\n", nome, preco, qtdEstoque);
	}
	
	
	
	
	
	
	
	
	
	
	
}
