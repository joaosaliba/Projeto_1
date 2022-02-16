package proj.teste.Deducoes;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import proj.principal.SimuladorIRPF;
import proj.principal.execptions.deducoes.DescricaoDeducaoEmBrancoException;

@RunWith(Parameterized.class)
public class Dependetes {
	
	//2.2
	
	private SimuladorIRPF simulador;
	@Before
	public void setuUp() {
		simulador = new SimuladorIRPF();
		
	}
	

	//Atributos do obj de teste
	Object[][] dependetes;
	float valorEsperado;
	
	
	//construtor obj de teste
	public Dependetes(Object[][] dependetes,float valorEsperado) {
		this.dependetes=dependetes;
		this.valorEsperado=valorEsperado;
	}
	@Parameters
	public static Collection<Object[]>getParametres(){
		
		Object[][]resposta = new Object[][] {
			{ new Object[][]{
				{"João","05/02/1997"}
							}, 189.59f },// caso1

			{new Object[][]{
				{"Pedro","05/02/1997"},
			}, 189.59f  },// caso2
			
			{ new Object[][]{
				{"João","05/02/1997"},
				{"Gabriel","05/02/1997"},
			}, 379.18f},// caso3
			
			{new Object[][]{
				{"João","05/02/1997"},
				{"Gabriel","05/02/1997"},
				{"Saliba","05/02/1997"},
			}, 568.77f},// caso4
			
		};
		
		return Arrays.asList(resposta);
	}
	
	@Test
	public void cadastroContribuicoes() throws ParseException, DescricaoDeducaoEmBrancoException {
		for(Object[]dependente:dependetes) {
		
			simulador.cadastraDependentes((String)dependente[0],(String)dependente[1]);
		}
		assertEquals(valorEsperado, simulador.getTotalDeducaoDependentes(),0f);
	}

}
