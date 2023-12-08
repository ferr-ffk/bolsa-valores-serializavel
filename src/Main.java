import modelo.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(Corretora.obterOrdens());

        List<Empresa> empresasCadastradas = Empresa.obterEmpresas();

        assert empresasCadastradas != null;
        Empresa empresa = empresasCadastradas.get(0);

        empresa.comprarCotas(3);

        Investidor investidor = new Investidor(832672365, "Davi Gomes", 2000.0f);

        Corretora corretora = new Corretora("Corretora tal", 9248635);

        corretora.enviarOrdem(empresa.obterAcao(), investidor, empresa);

        System.out.println(Corretora.obterOrdens().getLast());
    }
}