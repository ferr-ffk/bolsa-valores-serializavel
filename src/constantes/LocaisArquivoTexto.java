package constantes;

public abstract class LocaisArquivoTexto {

	public static String HISTORICO_ORDENS_SER_PADRAO = "HistoricoOrdens.ser";
	
	public static String EMPRESAS_SER_PADRAO = "Empresas.ser";

	public static String INVESTIDORES_SER_PADRAO = "Investidores.ser";

	/**
	 * Método estático para atualizar o caminho padrão da armazenagem dos arquivos de texto
	 *
	 * @param novoCaminho A String do caminho relativo da máquina ou da pasta à partir da raíz do projeto
	 */
	public static void setCaminhoArquivoPadrao(String novoCaminho) {
		EMPRESAS_SER_PADRAO = novoCaminho + "\\" + EMPRESAS_SER_PADRAO;
		INVESTIDORES_SER_PADRAO = novoCaminho + "\\" + INVESTIDORES_SER_PADRAO;
		HISTORICO_ORDENS_SER_PADRAO = novoCaminho + "\\" + HISTORICO_ORDENS_SER_PADRAO;
	}

}
