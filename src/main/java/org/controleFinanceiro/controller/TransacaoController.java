package org.controleFinanceiro.controller;

import org.controleFinanceiro.model.TipoTransacao;
import org.controleFinanceiro.model.Transacao;
import org.controleFinanceiro.service.TransacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    // POST /api/transacoes -> cadastra nova transação
    @PostMapping
    public String adicionarTransacao(@RequestBody Transacao transacao) {
        transacaoService.adicionarTransacao(transacao);
        return "Transação adicionada com sucesso!";
    }

    // GET /api/transacoes -> lista todas as transações
    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes();
    }

    // GET /api/transacoes/saldo -> exibe o saldo atual
    @GetMapping("/saldo")
    public double exibirSaldo() {
        return transacaoService.calcularSaldo();
    }

    // GET /api/transacoes/tipo/ENTRADA -> filtra por tipo
    @GetMapping("/tipo/{tipo}")
    public List<Transacao> listarPorTipo(@PathVariable TipoTransacao tipo) {
        return transacaoService.listarTransacoes()
                .stream()
                .filter(t -> t.getTipo() == tipo)
                .collect(Collectors.toList());

    }
}
