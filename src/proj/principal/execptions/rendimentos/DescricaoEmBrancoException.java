package proj.principal.execptions.rendimentos;

public class DescricaoEmBrancoException extends  Exception {
	
	public DescricaoEmBrancoException() {
		super("Descrição em branco");
	}
}
