package modelo;

import java.io.*;
import java.util.List;

import constantes.LocaisArquivoTexto;
import services.InvestidorService;

/**
 * A classe correspondente a um investidor qualquer na bolsa. Possui um saldo
 * para compra e uma lista de carteira de ações para compra.
 * 
 * @author Fernando Freitas, Davi Gomes
 */
public final class Investidor implements Serializable {

	@Serial
	private static final long serialVersionUID = -6793868738775707494l;
	private Integer codigo;

	private String nome;

	private double saldo;

	private final CarteiraDeAcoes carteiraDeAcoes;

	/**
	 * Instancia um novo investidor na bolsa.
	 * 
	 * @param codigo O código de investidor
	 * @param nome   O nome do investidor
	 * @param saldo  O saldo na carteira
	 */
	public Investidor(Integer codigo, String nome, double saldo) {
		this.codigo = codigo;
		this.nome = nome;
		this.saldo = saldo;
		this.carteiraDeAcoes = new CarteiraDeAcoes();

		registrarInvestidor(this);
	}

	/**
	 * Retorna todos os investidores cadastrados no banco de dados em arquivo de texto em forma de uma lista
	 *
	 * @return A lista de todos os investidores cadastrados
	 */
	public static List<Investidor> obterInvestidores() {
		return InvestidorService.readInvestidores();
	}

	public static Investidor obterInvestidor(Integer id) {
		return InvestidorService.readInvestidor(id);
	}

	private void registrarInvestidor(Investidor investidor) {
		InvestidorService.criarInvestidor(investidor);
	}

	private void atualizarInvestidor(Integer id, Investidor investidor) {
		InvestidorService.updateInvestidor(id, investidor);
	}

	public AbstratoAcao removerUltimoPapel() {
		return carteiraDeAcoes.removerUltimaAcao();
	}

	public void adicionarPapel(AbstratoAcao ap) {
		if (ap.calcularValor() > saldo) {
			throw new RuntimeException("A carteira não possui saldo suficiente!");
		}

		this.carteiraDeAcoes.adicionarAcao(ap);
		this.saldo -= ap.calcularValor();
	}

	public void comprarAcao(AbstratoAcao acao) {
		if (acao.calcularValor() > saldo) {
			throw new RuntimeException("Saldo insuficiente: " + saldo + " é menor que " + acao.calcularValor());
		}

		saldo -= acao.getValor();
		carteiraDeAcoes.adicionarAcao(acao);
	}

	public static void setCaminhoArquivo(String novoCaminho) {
		InvestidorService.setCaminhoArquivo(novoCaminho);
	}

	private Integer getIdNoBanco() {
		return InvestidorService.getIdInvestidor(this);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
		atualizarInvestidor(this.getIdNoBanco(), this);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		atualizarInvestidor(this.getIdNoBanco(), this);
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
		atualizarInvestidor(this.getIdNoBanco(), this);
	}

	@Override
	public String toString() {
		return codigo + ": {" + nome + ", saldo: " + saldo + "}";
	}
}
