package com.viavarejo.simulacao.compra.service;

import com.viavarejo.simulacao.compra.model.CondicaoPagamento;
import com.viavarejo.simulacao.compra.model.Produto;
import com.viavarejo.simulacao.compra.request.SimulacaoCompraRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class SimulacaoCompraTest {

    @InjectMocks
    private SimulacaoCompraService simulacaoCompraService;

    public void geraSimulacaoCompraTestSucess(){

        simulacaoCompraService.geraSimulacaoCompra(createSimulacaoCompraRequestMock());

    }


    public SimulacaoCompraRequest createSimulacaoCompraRequestMock(){
       return new SimulacaoCompraRequest()
               .setCondicaoPagamento(createCondicaoPagamento())
               .setProduto(createProduto());

    }

    private CondicaoPagamento createCondicaoPagamento() {
      return  new CondicaoPagamento()
                .setQtdeParcelas(5)
                .setValorEntrada(new BigDecimal("300"));
    }

    private Produto createProduto() {
       return new Produto()
                .setCodigo(1L)
                .setNome("tv")
                .setValor(new BigDecimal("2000"));

    }
}
