package com.viavarejo.simulacao.compra.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Document(collection = "condicaoPagamento")
public class CondicaoPagamento {

    @NotNull(message="valor da entrada não pode ser null")
    @Min(value=1, message = "valor da entrada não pode ser menor ou igual a zero")
    private BigDecimal valorEntrada;

    @NotNull(message="qtde Parcelas não pode ser null")
    @Min(value=1, message = "quantidade de parcelas não pode ser menor ou igual a zero")
    private Integer qtdeParcelas;
}
