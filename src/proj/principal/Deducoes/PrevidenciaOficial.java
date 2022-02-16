package proj.principal.Deducoes;

public class PrevidenciaOficial {

	private String descricao;
	private double valor;

	public PrevidenciaOficial(String descricao, float valor) {
		this.descricao = descricao; 
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}
}
