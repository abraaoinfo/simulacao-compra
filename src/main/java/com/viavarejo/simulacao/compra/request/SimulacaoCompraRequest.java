package com.viavarejo.simulacao.compra.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.viavarejo.simulacao.compra.model.CondicaoPagamento;
import com.viavarejo.simulacao.compra.model.Produto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SimulacaoCompraRequest {

    @Valid
    @NotNull(message="produto não pode ser null")
    private Produto produto;

    @Valid
    @NotNull(message="condicao de pagemnto não pode ser null")
    private CondicaoPagamento condicaoPagamento;

}
