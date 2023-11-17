package modelo;

import services.EmpresaService;
import util.Vetor;

import java.io.*;
import java.util.List;

/**
 * <p>
 * Uma empresa registrada na bolsa de valores terá componentes básicos de nome,
 * e código. Além de contar com o número de cotas e o valor delas.
 */
public class Empresa implements Serializable {

	@Serial
	private static final long serialVersionUID = -6132110895398037661L;
	private final String nome;

	private int cotas;

	private final float valorCota;

	private final String codigo;

	private final TipoAcaoEmpresa tipoAcaoEmpresa;

	private final Vetor<Empresa> empresasRelacionadas = new Vetor<>(25);

	/**
	 * Instancia uma nova empresa e logo registra ela no arquivo de texto.
	 *
	 * @param nome      O nome da empresa
	 * @param codigo    O código da empresa na bolsa de valores
	 * @param cotas     O número de cotas da empresa
	 * @param valorCota O valor de cada cota individual da empresa
	 */
	public Empresa(String nome, String codigo, int cotas, float valorCota, TipoAcaoEmpresa tipoAcaoEmpresa) {
		this.nome = nome;
		if (cotas <= 0) {
			throw new RuntimeException("O numero de cotas da empresa não pode ser inferior a zero!");
		}
		this.codigo = codigo;
		this.cotas = cotas;
		this.valorCota = valorCota;
		this.tipoAcaoEmpresa = tipoAcaoEmpresa;

		registrarEmpresa(this);
	}

	public int obterNumeroCotas() {
		return this.cotas;
	}

	public float obterValorEmpresa() {
		return this.cotas * valorCota;
	}

	public String getNome() {
		return nome;
	}

	public void relacionarEmpresa(Empresa empresa) {
		empresasRelacionadas.adicionar(empresa);
	}

	/**
	 * Altera o caminho de destino dos arquivos .ser
	 *
	 * @param caminho A string do caminho novo absoluto ou relativo da máquina
	 */
	public static void setCaminhoArquivo(String caminho) {
		EmpresaService.setCaminhoArquivo(caminho);
	}

	/**
	 * <p>
	 * O método utilizado para uma eventual ordem de compra de ações dessa empresa.
	 * <p>
	 * Cada empresa possui um número de ações (cotas), além de retirar uma cota
	 * dela.
	 * 
	 * @return uma acao da empresa
	 */
	public AbstratoAcao obterAcao() {
		comprarCotas(cotas);

		if (tipoAcaoEmpresa == TipoAcaoEmpresa.ACAO_FII) {

			return new AcaoFII("Acao FII", this.valorCota, 0.0f, 0.0f, cotas);
		} else if (tipoAcaoEmpresa == TipoAcaoEmpresa.ACAO_MERCADO) {

			return new AcaoMercado("Acao Mercado", this.valorCota, this.cotas, this);
		} else {
			throw new RuntimeException("Tipo de Acao da empresa nulo!");
		}
	}

	private void registrarEmpresa(Empresa empresa) {
		EmpresaService.criarEmpresa(empresa);
	}

	public void comprarCotas(int cotas) {
		if (this.cotas == 0 || this.cotas < cotas) {
			throw new RuntimeException("O numero de cotas da empresa não pode ser inferior a zero!");
		}
		this.cotas -= cotas;
	}

	/**
	 * Retorna todas as empresas registradas.
	 */
	public static List<Empresa> obterEmpresas() {
		return EmpresaService.readEmpresas();
	}

	/**
	 * <p> Busca no arquivo de texto uma empresa correspondente ao indice dado pelo usuário.
	 * <p> Se não for encontrado, retorna uma string vazia
	 * 
	 * @param indice O índice do elemento
	 * @return A string da empresa
	 */
	public static Empresa obterEmpresa(Integer indice) {
		return EmpresaService.readEmpresa(indice);
	}

	/**
	 * Obtém e atualiza uma empresa no banco de dados virtual e no arquivo de texto
	 *
	 * @param id O id da empresa
	 * @param empresa O novo objeto da empresa a ser atualizada
	 */
	public static void atualizarEmpresa(Integer id, Empresa empresa) {
		EmpresaService.updateEmpresa(id, empresa);
	}

	/**
	 * Remove uma empresa do arquivo de texto, junto do banco de dados virtual
	 *
	 * @param id O id da empresa a ser removida
	 * @return true se a empresa existir, false se não foi encontrada
	 */
	public static boolean removerEmpresa(Integer id) {
		return EmpresaService.deleteEmpresa(id);
	}

	@Override
	public String toString() {
		return "{ Empresa: " + nome + " \"" + codigo + "\", total de cotas: " + cotas + ", valor da empresa: "
				+ obterValorEmpresa() + "}";
	}
}