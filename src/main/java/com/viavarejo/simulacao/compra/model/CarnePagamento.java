package com.viavarejo.simulacao.compra.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        BigDecimal valParcela = calculaValorParcela(valorSerPago);

        criandoParcelas(qtdParcela, juros, valParcela);

    }

    private void criandoParcelas(Integer qtdParcela, BigDecimal juros, BigDecimal valParcela) {
        for (int numParcela = 1; numParcela <= qtdParcela; numParcela++) {

            if(numParcela ==1){
                BigDecimal valoPrimeiraParcela = verificaAjusteValorPrimeiraParcela(valParcela);
                parcelas.add(new Parcela(numParcela, valoPrimeiraParcela, juros));
            }else {
                parcelas.add(new Parcela(numParcela, valParcela, juros));
            }
        }
    }

    private BigDecimal verificaAjusteValorPrimeiraParcela(BigDecimal valParcela) {

        log.info("verifica_ser_total_parcelado_e_igual_ao_total_a_pagar");

        BigDecimal valorTodasParcelas = valParcela.multiply(new BigDecimal(qtdParcela));
        BigDecimal diferenca = valorSerPago.subtract(valorTodasParcelas);

        if(diferenca.compareTo(BigDecimal.ZERO) >0){
            log.info("adicionado difernça na primeira parcela");
            valParcela = valParcela.add(diferenca);
        }

        return valParcela;
    }

    private BigDecimal calculaValorParcela( BigDecimal valorSerPago) {

        log.info("calculando valor da parcela");

        return valorSerPago.divide(new BigDecimal(qtdParcela),2, BigDecimal.ROUND_DOWN);

    }











}
