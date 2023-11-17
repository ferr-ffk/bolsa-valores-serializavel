import modelo.*;
import services.EmpresaService;
import services.InvestidorService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Empresa empresa = Empresa.obterEmpresa(1);
        Investidor investidor = Investidor.obterInvestidores().get(1);

        System.out.println(investidor);
        System.out.println(empresa);

        AbstratoAcao acao = new AcaoFII("Acão X", 29.9f, 3.1f, 4.3f, 4);

        Corretora corretora = new Corretora("Corretora Rodriguinho Bet", 1285129);

        corretora.enviarOrdem(acao, investidor, empresa);

        System.out.println(Corretora.obterOrdens());
    }
}