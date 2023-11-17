package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import constantes.LocaisArquivoTexto;
import services.CorretoraService;
import util.Pilha;

/**
 * <p>
 * A classe corretora representa uma empresa responsável por enviar ordens de
 * compra e venda entre um investidor e a empresa vendendo as cotas.
 * 
 * 
 * @author Fernando Freitas, Davi Gomes
 */
public class Corretora implements Serializable {

	@Serial
	private static final long serialVersionUID = -5802901772382038450L;
	private String nome;

	private Integer codigo;

	private Pilha<Ordem> ordensEfetuadas = new Pilha<>();

	/**
	 * Instancia uma nova corretora na bolsa de valores.
	 * 
	 * @param nome   O nome da corretora
	 * @param codigo O código da corretora
	 */
	public Corretora(String nome, Integer codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}

	/**
	 * <p>Altera o caminho padrão para armazenamento do histórico das ordens</p>
	 *
	 * @param caminho O novo caminho absoluto da máquina
	 */
	public static void setCaminhoArquivo(String caminho) {
		CorretoraService.setCaminhoArquivo(caminho);
	}

	/**
	 * Realiza a ordem de compra para o investidor, além de registrar no arquivo de
	 * texto. Para total segurança, certifica que o investidor tem poder de compra e
	 * depois adiciona em sua carteira de ações. Posteriormente subtrai da empresa
	 * uma cota.
	 * 
	 * 
	 * @param a A ação a ser comprada pelo investidor
	 * @param i O investidor realizando o papel de comprador
	 * @param emp A empresa a vender a cota
	 */
	public void enviarOrdem(AbstratoAcao a, Investidor i, Empresa emp) {
		CorretoraService.enviarOrdem(new Ordem(i, this, a, emp));
	}

	public void removerUltimaOrdem() {
		Ordem ultimaOrdem = ordensEfetuadas.desempilhar();
		System.out.println(ultimaOrdem + " removida com sucesso");
	}

	public static ArrayList<Ordem> obterOrdens() {
		return CorretoraService.readOrdens();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return codigo +": {" + nome + ", " + ordensEfetuadas + "}";
	}
}
