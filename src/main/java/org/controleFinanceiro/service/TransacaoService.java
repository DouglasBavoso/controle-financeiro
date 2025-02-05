package org.controleFinanceiro.service;

import org.controleFinanceiro.model.TipoTransacao;
import org.controleFinanceiro.model.Transacao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransacaoService {
    private final List<Transacao> lista = new ArrayList<>();

    public void adicionarTransacao(Transacao transacao) {
        lista.add(transacao);
    }

    public List<Transacao> listarTransacoes() {
        return Collections.unmodifiableList(lista);
    }

    public double calcularSaldo() {
        double saldo = 0.0;
        for (Transacao t : lista) {
            saldo += t.getTipo() == TipoTransacao.ENTRADA ? t.getValor() : -t.getValor();
        }
        return saldo;
    }
}