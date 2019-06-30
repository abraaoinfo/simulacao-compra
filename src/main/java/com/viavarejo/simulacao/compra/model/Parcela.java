package com.viavarejo.simulacao.compra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parcela {
    private Integer numParcela;
    private BigDecimal valor;
    private BigDecimal taxaJuros;

}
