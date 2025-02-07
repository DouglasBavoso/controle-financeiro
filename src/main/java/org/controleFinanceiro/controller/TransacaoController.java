package org.controleFinanceiro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.controleFinanceiro.model.TipoTransacao;
import org.controleFinanceiro.model.Transacao;
import org.controleFinanceiro.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Cadastra uma nova transação", description = "Insere uma transação no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados enviados")
    })
    // Criar uma nova transação
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarTransacao(@RequestBody Transacao transacao) {
        transacaoService.adicionarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transação cadastrada com sucesso!");
    }

    // Listar todas as transações
    @GetMapping("/listar")
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes();
    }

    // Filtrar transações por tipo (ENTRADA/SAIDA)
    @GetMapping("/listar/tipo/{tipo}")
    public List<Transacao> listarPorTipo(@PathVariable TipoTransacao tipo) {
        return transacaoService.listarTransacoes()
                .stream()
                .filter(t -> t.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    // Consultar saldo
    @GetMapping("/consultar-saldo")
    public double consultarSaldo() {
        return transacaoService.calcularSaldo();
    }
}
