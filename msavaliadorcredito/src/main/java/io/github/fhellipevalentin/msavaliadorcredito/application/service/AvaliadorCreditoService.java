package io.github.fhellipevalentin.msavaliadorcredito.application.service;

import feign.FeignException;
import io.github.fhellipevalentin.msavaliadorcredito.application.model.CartaoCliente;
import io.github.fhellipevalentin.msavaliadorcredito.application.model.DadosCliente;
import io.github.fhellipevalentin.msavaliadorcredito.application.model.SituacaoCliente;
import io.github.fhellipevalentin.msavaliadorcredito.exceptions.DadosClienteNotFoundException;
import io.github.fhellipevalentin.msavaliadorcredito.exceptions.ErroComunicacaoMicroservicesException;
import io.github.fhellipevalentin.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.fhellipevalentin.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;
    private final CartoesResourceClient cartoesResourceClient;

    public SituacaoCliente obterSituacaoCliente (String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException{
        // obterDadosCliente - MSCLIENTES
        // obterCartoesCliente - MSCARTOES
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesResourceClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        }
        catch (FeignException.FeignClientException e) {
              int status = e.status();
              if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
              } else {
                  throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
              }
        }
    }

}
