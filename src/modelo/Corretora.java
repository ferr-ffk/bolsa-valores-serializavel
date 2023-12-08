package modelo;

import java.io.*;
import java.util.ArrayList;

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

	private final Pilha<Ordem> ordensEfetuadas = new Pilha<>();

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
	 * @param acao A ação a ser comprada pelo investidor
	 * @param investidor O investidor realizando o papel de comprador
	 * @param empresa A empresa a vender a cota
	 */
	public void enviarOrdem(AbstratoAcao acao, Investidor investidor, Empresa empresa) {
		try {
			CorretoraService.enviarOrdem(new Ordem(investidor, this, acao, empresa));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void removerUltimaOrdem() {
		Ordem ultimaOrdem = ordensEfetuadas.desempilhar();
		System.out.println(ultimaOrdem + " removida com sucesso");
	}

	/**
	 * Retorna a lista de todas as ordens efetuadas cadastradas no arquivo
	 *
	 * @return A lista de ordens efetuadas, nulo se não for realizada nenhuma no momento
	 */
	public static ArrayList<Ordem> obterOrdens() {
		try {
			return CorretoraService.readOrdens();
		} catch (Exception e) {
			return null;
		}
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
