package modelo;

public class AcaoFII extends AbstratoAcao {

	private final double cotas;

	public AcaoFII(String nome, float valor, float valorEntrada, float valorFechado, int cotas) {
		super(nome, valor, valorEntrada, valorFechado);
		this.cotas = cotas;
	}
	
	@Override
	public double calcularValor() {
		return valor * cotas;
	}
	
}
