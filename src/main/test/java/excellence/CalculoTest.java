package excellence;

import junit.framework.TestCase;

public class CalculoTest extends TestCase{
	public void testeExecutaCalculo() {
		
		float valor1 = 10;
		float valor2 = 5;
		float esperado = 15;
		
		float retorno = Calculo.executaCalculo(valor1, valor2);
		
		assertEquals(esperado, retorno);
	}
}
