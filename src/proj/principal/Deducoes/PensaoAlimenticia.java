package proj.principal.Deducoes;

public class PensaoAlimenticia {
	
	private String descricao;
	private double valorPensao;

	public PensaoAlimenticia(String descricao, float valorPensao) {
		this.descricao = descricao; 
		this.valorPensao = valorPensao;
	}

	public double getValorPensao() {
		return valorPensao;
	}

	
}
