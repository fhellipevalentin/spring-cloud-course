package io.github.fhellipevalentin.msavaliadorcredito.infra.clients;

import io.github.fhellipevalentin.msavaliadorcredito.application.model.Cartao;
import io.github.fhellipevalentin.msavaliadorcredito.application.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartaoRendaUntil(@RequestParam("renda") Long renda);

}
