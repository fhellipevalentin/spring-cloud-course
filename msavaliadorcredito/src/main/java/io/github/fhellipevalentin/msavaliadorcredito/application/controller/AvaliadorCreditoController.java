package io.github.fhellipevalentin.msavaliadorcredito.application.controller;

import io.github.fhellipevalentin.msavaliadorcredito.application.model.RetornoAvaliacaoCliente;
import io.github.fhellipevalentin.msavaliadorcredito.application.model.SituacaoCliente;
import io.github.fhellipevalentin.msavaliadorcredito.application.service.AvaliadorCreditoService;
import io.github.fhellipevalentin.msavaliadorcredito.exceptions.DadosClienteNotFoundException;
import io.github.fhellipevalentin.msavaliadorcredito.exceptions.ErroComunicacaoMicroservicesException;
import io.github.fhellipevalentin.msavaliadorcredito.application.model.DadosAvaliacao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status() {
        return "ok";
    }

    // avaliacoes-credito/situacao-cliente?cpf=01234567890
    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados ) {
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

}
