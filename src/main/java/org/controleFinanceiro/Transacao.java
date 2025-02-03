package org.controleFinanceiro;

import java.time.LocalDate;

public class Transacao {
    private String descricao;
    private double valor;
    private LocalDate data;
    private TipoTransacao tipo;

    public Transacao(String descricao, double valor, LocalDate data, TipoTransacao tipo) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s: R$ %.2f em %s",
                tipo,
                descricao,
                valor,
                data
        );
    }
}
