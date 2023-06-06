package io.github.fhellipevalentin.msavaliadorcredito.application.model;

import io.github.fhellipevalentin.msavaliadorcredito.application.model.CartaoAprovado;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovado> cartoes;
}
