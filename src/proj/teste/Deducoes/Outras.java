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
public class Outras {
	//	2.4
	private SimuladorIRPF simulador;
	@Before
	public void setuUp() {
		simulador = new SimuladorIRPF();
		
	}
	
	
	//Atributos do obj de teste
	Object[][] outras;
	float valorEsperado;
	
	
	//construtor obj de teste
	public Outras(Object[][] outras,float valorEsperado) {
		this.outras=outras;
		this.valorEsperado=valorEsperado;
	}
	
	@Parameters
	public static Collection<Object[]>getParametres(){
		Object[][]resposta = new Object[][] {
			{ new Object[][]{
				{"Outra Dedução 1",1000f}
							}, 1000f },// caso1

			{new Object[][]{
				{"Outra Dedução 1",800f},
			}, 800f  },// caso2
			
			{ new Object[][]{
				{"Outra Dedução 1",1000f},
				{"Outra Dedução 2",800f},
			}, 1800f },// caso3
			
			{ new Object[][]{
				{"Outra Dedução 1",1000f},
				{"Outra Dedução 2",800f},
				{"Outra Dedução 3",200f},
			}, 2000f },// caso4
			
		};
		
		return Arrays.asList(resposta);
	}
	
	
	
	
	@Test
	public void cadastroOutas() throws ValorDeducaoInvalidoException, DescricaoDeducaoEmBrancoException {
		for(Object[]outra:outras) {
			simulador.cadastraOutras((String)outra[0], (float)outra[1]);
		}
		assertEquals(valorEsperado, simulador.getTotalDeducaoOutras(),0f);
	}

}
