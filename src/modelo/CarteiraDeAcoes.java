package modelo;

import util.Fila;

import java.io.Serial;
import java.io.Serializable;

public class CarteiraDeAcoes implements Serializable {

	@Serial
	private static final long serialVersionUID = 6277502854456809520L;
	private Fila<AbstratoAcao> acoes;
	
	public CarteiraDeAcoes() {
		acoes = new Fila<AbstratoAcao>();
	}
	
	public void adicionarAcao(AbstratoAcao a) {
		acoes.adicionar(a);
	}
	
	public AbstratoAcao removerUltimaAcao() {
		return acoes.remover();
	}
	
	@Override
	public String toString() {
		return acoes.toString();
	}
}
