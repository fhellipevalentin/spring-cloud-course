package io.github.fhellipevalentin.msavaliadorcredito.exceptions;

public class DadosClienteNotFoundException extends Exception{
     public DadosClienteNotFoundException() {
         super("Dados do Cliente não encontrados para o CPF informado");
     }

}
