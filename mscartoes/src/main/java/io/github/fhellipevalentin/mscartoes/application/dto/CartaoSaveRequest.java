package io.github.fhellipevalentin.mscartoes.application.dto;

import io.github.fhellipevalentin.mscartoes.application.enums.BandeiraCartao;
import io.github.fhellipevalentin.mscartoes.application.model.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel() {
        return new Cartao(nome, bandeira, renda, limite);
    }

}
