package com.viavarejo.simulacao.compra.service;

import com.viavarejo.simulacao.compra.model.CondicaoPagamento;
import com.viavarejo.simulacao.compra.model.Parcela;
import com.viavarejo.simulacao.compra.model.Produto;
import com.viavarejo.simulacao.compra.model.TaxaJuro;
import com.viavarejo.simulacao.compra.request.SimulacaoCompraRequest;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import static org.mockito.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class SimulacaoCompraTest {

    @InjectMocks
    private SimulacaoCompraService simulacaoCompraService;

    @Mock
    private RestTemplate restTemplate;

    public void geraSimulacaoCompraTaxJurosTestSucess() {

        List<Parcela> parcelas = simulacaoCompraService.geraSimulacaoCompra(createSimulacaoCompraRequestMock());


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
