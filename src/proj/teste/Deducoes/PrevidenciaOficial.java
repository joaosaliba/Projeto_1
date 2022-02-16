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
public class PrevidenciaOficial {
	
	// Triangulação via teste parametrizado
	
	//https://www27.receita.fazenda.gov.br/simulador-irpf/
	
//	2.1
	private SimuladorIRPF simulador;
	@Before
	public void setuUp() {
		simulador = new SimuladorIRPF();
		
	}
	
	
	//Atributos do obj de teste
	Object[][] contribuicoes;
	float valorEsperado;
	
	
	//construtor obj de teste
	public PrevidenciaOficial(Object[][] contribuicoes,float valorEsperado) {
		this.contribuicoes=contribuicoes;
		this.valorEsperado=valorEsperado;
	}
	
	@Parameters
	public static Collection<Object[]>getParametres(){
//		{{descricao,valor},valoresperado}
//		{{desc1,valor1},
//		{{desc2,valor2},
//		{{desc3,valor3},
//		valorEsperado}
		
		Object[][]resposta = new Object[][] {
			{ new Object[][]{
				{"Contribuição Compulsoria",1000f}
							}, 1000f },// caso1

			{new Object[][]{
				{"Contribuição Compulsoria",800f},
			}, 800f  },// caso2
			
			{ new Object[][]{
				{"Contribuição Compulsoria",1000f},
				{"Carne InSS",800f},
			}, 1800f },// caso3
			
			{ new Object[][]{
				{"Contribuição Compulsoria",1000f},
				{"Carne InSS1",800f},
				{"Carne InSS2",200f},
			}, 2000f },// caso4
			
		};
		
		return Arrays.asList(resposta);
	}
	
	
	
	
	@Test
	public void cadastroContribuicoes() throws DescricaoDeducaoEmBrancoException, ValorDeducaoInvalidoException {
		for(Object[]contribuicao:contribuicoes) {
			simulador.cadastraPrevidenciaOficial((String)contribuicao[0], (float)contribuicao[1]);
		}
		assertEquals(valorEsperado, simulador.getTotalDeducaoPrevidenciaOficial(),0f);
	}
	
	
	
//	@Test
//	public void testeCadastroUmaPrevOficial() {
//		simulador.cadastraPrevidenciaOficial("Contribuição compulsoria", 1000f);
//		assertEquals(1000f, simulador.getTotalDeducaoPrevidenciaOficial(),0f);
//	}
//	
//	@Test
//	public void testeOutraCadastroUmaPrevOficial() {
//		simulador.cadastraPrevidenciaOficial("Contribuição compulsoria", 800f);
//		assertEquals(800f, simulador.getTotalDeducaoPrevidenciaOficial(),0f);
//	}
//	
//	@Test
//	public void testeoutraCadastroDuasPrevOficial() {
//		simulador.cadastraPrevidenciaOficial("Contribuição compulsoria", 1000f);
//		simulador.cadastraPrevidenciaOficial("CarneInss", 800f);
//		assertEquals(1800f, simulador.getTotalDeducaoPrevidenciaOficial(),0f);
//	}
//	
//	@Test
//	public void testeoutraCadastroTresPrevOficial() {
//		simulador.cadastraPrevidenciaOficial("Contribuição compulsoria", 1000f);
//		simulador.cadastraPrevidenciaOficial("CarneInss", 800f);
//		simulador.cadastraPrevidenciaOficial("CarneInss 2", 200f);
//		assertEquals(2000f, simulador.getTotalDeducaoPrevidenciaOficial(),0f);
//	}
//	
	
}
