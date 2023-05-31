package io.github.fhellipevalentin.mscartoes.application.service;

import io.github.fhellipevalentin.mscartoes.application.model.ClienteCartao;
import io.github.fhellipevalentin.mscartoes.infra.respository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
