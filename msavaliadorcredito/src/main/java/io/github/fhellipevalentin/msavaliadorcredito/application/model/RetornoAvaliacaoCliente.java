package io.github.fhellipevalentin.msavaliadorcredito.model;

import lombok.Data;

import java.util.List;

@Data
public class RetornoAvaliacaoCliente {
    private List<CartaoAprovado> cartoes;
}
