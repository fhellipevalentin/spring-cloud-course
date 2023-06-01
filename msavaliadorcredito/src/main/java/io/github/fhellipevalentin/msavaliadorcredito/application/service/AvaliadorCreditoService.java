package io.github.fhellipevalentin.msavaliadorcredito.application.service;

import io.github.fhellipevalentin.msavaliadorcredito.application.model.DadosCliente;
import io.github.fhellipevalentin.msavaliadorcredito.application.model.SituacaoCliente;
import io.github.fhellipevalentin.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;

    public SituacaoCliente obterSituacaoCliente (String cpf) {
        // obterDadosCliente - MSCLIENTES
        // obterCartoesCliente - MSCARTOES
        ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .build();
    }
}
