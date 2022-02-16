package proj.teste.Deducoes;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import proj.principal.SimuladorIRPF;
import proj.principal.execptions.deducoes.DescricaoDeducaoEmBrancoException;
import proj.principal.execptions.deducoes.ValorDeducaoInvalidoException;

@RunWith(Parameterized.class)
public class PensaoAlimenticia {

//	2.1
	private SimuladorIRPF simulador;
	@Before
	public void setuUp() {
		simulador = new SimuladorIRPF();
		
	}
	
	
	//Atributos do obj de teste
	Object[][] pensoes;
	float valorEsperado;
	
	
	//construtor obj de teste
	public PensaoAlimenticia(Object[][] pensoes,float valorEsperado) {
		this.pensoes=pensoes;
		this.valorEsperado=valorEsperado;
	}
	
	@Parameters
	public static Collection<Object[]>getParametres(){
		Object[][]resposta = new Object[][] {
			{ new Object[][]{
				{"Pensao Alimenticia 1",1000f}
							}, 1000f },// caso1

			{new Object[][]{
				{"Pensao Alimenticia 1",800f},
			}, 800f  },// caso2
			
			{ new Object[][]{
				{"Pensao Alimenticia 1",1000f},
				{"Pensao Alimenticia 2",800f},
			}, 1800f },// caso3
			
			{ new Object[][]{
				{"Pensao Alimenticia 1",1000f},
				{"Pensao Alimenticia 2",800f},
				{"Pensao Alimenticia 3",200f},
			}, 2000f },// caso4
			
		};
		
		return Arrays.asList(resposta);
	}
	
	
	
	
	@Test
	public void cadastroPensao() throws DescricaoDeducaoEmBrancoException, ValorDeducaoInvalidoException {
		for(Object[]pensao:pensoes) {
			simulador.cadastraPensaoAlimenticia((String)pensao[0], (float)pensao[1]);
		}
		assertEquals(valorEsperado, simulador.getTotalDeducaoPensaoAlimenticia(),0f);
	}
}
