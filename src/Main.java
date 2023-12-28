import constantes.LocaisArquivoTexto;
import modelo.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        LocaisArquivoTexto.setCaminhoArquivoPadrao("C:\\Users\\fe_fr\\Documents");

        System.out.println(Investidor.obterInvestidores());
    }
}