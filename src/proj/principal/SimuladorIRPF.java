package proj.principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import proj.principal.Deducoes.Dependente;
import proj.principal.Deducoes.Outra;
import proj.principal.Deducoes.PensaoAlimenticia;
import proj.principal.Deducoes.PrevidenciaOficial;
import proj.principal.Deducoes.Rendimento;
import proj.principal.execptions.deducoes.DescricaoDeducaoEmBrancoException;
import proj.principal.execptions.deducoes.ValorDeducaoInvalidoException;
import proj.principal.execptions.rendimentos.DescricaoEmBrancoException;
import proj.principal.execptions.rendimentos.ValorRendimentoInvalidoException;


public class SimuladorIRPF {

	private float totalRendimentos;
	private List<Rendimento> rendimentos;
	private List<PrevidenciaOficial> previdenciaslOficial;
	private List<PensaoAlimenticia> pensoesAlimenticia;
	private List<Outra> outras;
	private List<Dependente> dependentes ;
	private float custoDependente ;
	
	public SimuladorIRPF() {
		this.rendimentos = new LinkedList<Rendimento>();
		this.previdenciaslOficial =new LinkedList<PrevidenciaOficial>();
		this.dependentes=new LinkedList<Dependente>();
		this.pensoesAlimenticia =new LinkedList<PensaoAlimenticia>();
		this.outras =new LinkedList<Outra>();
		this.custoDependente =189.59f;
	}

	public void cadastrarRendimento(String descricao, float valorRendimento) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
		
		if(descricao.trim().length()<=1) {
			throw new DescricaoEmBrancoException();
		}
		if(valorRendimento<=0) {
			throw new  ValorRendimentoInvalidoException();
		}
			
		Rendimento r = new Rendimento(descricao, valorRendimento);
		this.rendimentos.add(r);
		
		this.totalRendimentos += valorRendimento;
	}

	public float getTotalRendimentos() {
		return totalRendimentos;
	}

	public void cadastraDependentes(String nome,String dataNascimento) throws ParseException, DescricaoDeducaoEmBrancoException {
		if(nome.trim().length()<=1) {
			throw new DescricaoDeducaoEmBrancoException();
		}
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date data = formato.parse(dataNascimento);
		Dependente d = new Dependente(nome,data);
		this.dependentes.add(d);
		
		
	}
	public float getTotalDeducaoDependentes() {
		return this.dependentes.size()*custoDependente;
	}
	

	public void cadastraPensaoAlimenticia(String descricao, float valor) throws DescricaoDeducaoEmBrancoException, ValorDeducaoInvalidoException {
		
		if(descricao.trim().length()<=1) {
			throw new DescricaoDeducaoEmBrancoException();
		}
		if(valor<=0) {
			throw new  ValorDeducaoInvalidoException();
		}
		PensaoAlimenticia pensao = new PensaoAlimenticia(descricao,valor);
		this.pensoesAlimenticia.add(pensao);
	}

	public float getTotalDeducaoPensaoAlimenticia() {
		return (float)this.pensoesAlimenticia.stream().mapToDouble(pensao -> pensao.getValorPensao()).sum();
	}
	
	public void cadastraOutras(String descricao, float valor) throws  ValorDeducaoInvalidoException, DescricaoDeducaoEmBrancoException {
		if(descricao.trim().length()<=1) {
			throw new DescricaoDeducaoEmBrancoException();
		}
		if(valor<=0) {
			throw new  ValorDeducaoInvalidoException();
		}
		Outra outra = new Outra(descricao, valor);
		this.outras.add(outra);
		
	}

	public float getTotalDeducaoOutras() {
		return (float)this.outras.stream().mapToDouble(outra-> outra.getValor()).sum();
	}

	public void cadastraPrevidenciaOficial(String descricao, float valor) throws DescricaoDeducaoEmBrancoException, ValorDeducaoInvalidoException {
		if(descricao.trim().length()<=1) {
			throw new DescricaoDeducaoEmBrancoException();
		}
		if(valor<=0) {
			throw new  ValorDeducaoInvalidoException();
		}
		PrevidenciaOficial prev = new PrevidenciaOficial(descricao, valor);
		this.previdenciaslOficial.add(prev);
	}

	public float getTotalDeducaoPrevidenciaOficial() {
		return (float)this.previdenciaslOficial.stream().mapToDouble(prev-> prev.getValor()).sum();
	}
	
	


	

}
