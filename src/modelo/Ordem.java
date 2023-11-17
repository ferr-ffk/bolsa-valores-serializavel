package modelo;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.CoderResult;

public class Ordem implements Serializable {

    @Serial
    private static final long serialVersionUID = 5508533389998838481L;
    private Investidor investidor;

    private Corretora corretora;

    private AbstratoAcao acao;

    private Empresa empresa;

    public Ordem() {}

    public Ordem(Investidor investidor, Corretora corretora, AbstratoAcao acao, Empresa empresa) {
        this.investidor = investidor;
        this.corretora = corretora;
        this.acao = acao;
        this.empresa = empresa;
    }

    public AbstratoAcao getAcao() {
        return acao;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public Corretora getCorretora() {
        return corretora;
    }

    @Override
    public String toString() {
        return String.format("Ordem enviada a %s da empresa %s, feita pela corretora %s", investidor, empresa, corretora);
    }
}
