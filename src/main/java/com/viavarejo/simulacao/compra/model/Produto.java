package com.viavarejo.simulacao.compra.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Produto {

    @NotNull(message="codigo do produto não pode ser null")
    @Min(value=1, message = "codigo do produto não pode ser menor ou igual a zero")
    private Long codigo;

    @NotBlank(message="nome do produto não pode ser null")
    private String nome;

    @NotNull(message="valor produto não pode ser null")
    @Min(value=1, message = "codigo do produto não pode ser menor ou igual a zero")
    private BigDecimal valor;


}
