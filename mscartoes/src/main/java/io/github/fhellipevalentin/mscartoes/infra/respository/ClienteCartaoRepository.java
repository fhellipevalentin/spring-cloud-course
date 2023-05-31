package io.github.fhellipevalentin.mscartoes.infra.respository;

import io.github.fhellipevalentin.mscartoes.application.model.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}
