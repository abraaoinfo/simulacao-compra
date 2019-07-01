package com.viavarejo.simulacao.compra.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Slf4j
@Data
public class Parcela {


    private Integer numParcela;

    private BigDecimal valor;
    private BigDecimal taxaJuros;


    public Parcela(Integer numParcela, BigDecimal valor, BigDecimal taxaJuros) {

        Assert.notNull(numParcela, "numero_parcela_nao_poder_ser_null");
        Assert.notNull(valor, "numero_parcela_nao_poder_ser_null");
        Assert.notNull(taxaJuros, "taxa_juros_nao_poder_ser_null");

        this.numParcela = numParcela;
        this.valor = valor;
        this.taxaJuros = taxaJuros;
    }

      public BigDecimal getValor(){
        
        if(taxaJuros.compareTo(BigDecimal.ZERO)>0) {
            BigDecimal percJuros = taxaJuros.divide(new BigDecimal("100")).add(BigDecimal.ONE);
            this.valor =  this.valor.multiply(percJuros).setScale(2, BigDecimal.ROUND_DOWN);
        }

        return this.valor;
    }


}
