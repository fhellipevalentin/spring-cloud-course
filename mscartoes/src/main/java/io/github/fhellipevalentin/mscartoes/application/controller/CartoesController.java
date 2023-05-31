package io.github.fhellipevalentin.mscartoes.application.controller;

import io.github.fhellipevalentin.mscartoes.application.dto.CartaoSaveRequest;
import io.github.fhellipevalentin.mscartoes.application.dto.CartoesPorClienteResponse;
import io.github.fhellipevalentin.mscartoes.application.model.Cartao;
import io.github.fhellipevalentin.mscartoes.application.model.ClienteCartao;
import io.github.fhellipevalentin.mscartoes.application.service.CartaoService;
import io.github.fhellipevalentin.mscartoes.application.service.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesController {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request) {
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    // /cartoes?=renda=5000
    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartaoRendaUntil(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return  ResponseEntity.ok(list);
    }

@GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
    List<ClienteCartao> lista = clienteCartaoService.listCartoesByCpf(cpf);
    List<CartoesPorClienteResponse> resultList = lista.stream()
            .map(CartoesPorClienteResponse::fromModel)
            .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

}
