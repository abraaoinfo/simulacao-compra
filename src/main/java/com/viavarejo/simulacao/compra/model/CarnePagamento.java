package com.viavarejo.simulacao.compra.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class CarnePagamento {

    private BigDecimal valorSerPago;
    private Integer qtdParcela;
    private BigDecimal juros;
    private List<Parcela> parcelas = new ArrayList<>();

    public CarnePagamento(Integer qtdParcela, BigDecimal valorSerPago, BigDecimal juros) {
        log.info("criando carne de pagamento");

        this.qtdParcela = qtdParcela;
        this.valorSerPago = valorSerPago;
        this.juros =juros;

        BigDecimal valParcela = calculaValorParcela(valorSerPago, juros);

        for (int i = 1; i <= qtdParcela; i++) {
            parcelas.add(new Parcela(i, valParcela, juros));
        }
    }
    private BigDecimal calculaValorParcela( BigDecimal valorSerPago, BigDecimal juros) {

        log.info("calculando valor da parcela");

        BigDecimal valParcela = valorSerPago.divide(new BigDecimal(qtdParcela));
        if (juros.compareTo(BigDecimal.ZERO) > 0) {
            valParcela = adcionaJurosParcela(valParcela, juros);
        }
        return valParcela;
    }

    private BigDecimal  adcionaJurosParcela(BigDecimal valParcela, BigDecimal juros){
        log.info("adicionando juroa ao valor da parcela");

        BigDecimal percJuros = juros.divide(new BigDecimal("100")).add(BigDecimal.ONE);
        return valParcela.multiply(percJuros);

    }


}
