package org.controleFinanceiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransacaoService {
    private List<Transacao> lista = new ArrayList<Transacao>();

    public void adicionarTransacao(Transacao transacao) {
        lista.add(transacao);
    }

    public List<Transacao> listarTransacoes() {
        // Retorna uma lista imutável para evitar modificações diretas fora do serviço
        return Collections.unmodifiableList(lista);
    }

    public double calcularSaldo() {
        double saldo = 0.0;
        for (Transacao t : lista) {
            if (t.getTipo() == TipoTransacao.ENTRADA) {
                saldo += t.getValor();
            } else {
                saldo -= t.getValor();
            }
        }
        return saldo;
    }
}
