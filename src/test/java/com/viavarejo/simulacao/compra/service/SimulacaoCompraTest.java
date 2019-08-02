package com.viavarejo.simulacao.compra.service;

import com.viavarejo.simulacao.compra.model.CondicaoPagamento;
import com.viavarejo.simulacao.compra.model.Parcela;
import com.viavarejo.simulacao.compra.model.Produto;
import com.viavarejo.simulacao.compra.model.TaxaJuro;
import com.viavarejo.simulacao.compra.request.SimulacaoCompraRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimulacaoCompraTest {

    @InjectMocks
    private SimulacaoCompraService simulacaoCompraService;

    @Mock
    private RestTemplate restTemplate;


    @Before
    public void init(){
        ReflectionTestUtils.setField(simulacaoCompraService, "uriServiceTaxaSelic", "http://teste");
    }

    @Test
    public void geraSimulacaoCompraTestSucess(){

        SimulacaoCompraRequest simulacaoCompraRequestMock = createSimulacaoCompraRequestMock();

        List<Parcela> parcelas  = simulacaoCompraService.
                geraSimulacaoCompra(simulacaoCompraRequestMock);

        Assert.assertTrue(parcelas.size() == 5);
        Assert.assertTrue(parcelas.get(0).getValor().compareTo(new BigDecimal("340"))==0);

    }

    @Test
    public void geraSimulacaoCompraComTaxaJurosTestSucess(){

        SimulacaoCompraRequest simulacaoCompraRequestMock1 = createSimulacaoCompraRequestMock();
        simulacaoCompraRequestMock1.getCondicaoPagamento().setQtdeParcelas(10);
        TaxaJuro[] taxaJuros = createTaxaJuros();
        when(restTemplate.getForObject( anyString(), any())).thenReturn(taxaJuros);

        List<Parcela> parcelas  = simulacaoCompraService.
                geraSimulacaoCompra(simulacaoCompraRequestMock1);

        Assert.assertTrue(parcelas.size() == 10);
        Assert.assertTrue(parcelas.get(0).getValor().compareTo(new BigDecimal("100.5"))==0);

    }


    private SimulacaoCompraRequest createSimulacaoCompraRequestMock(){
        SimulacaoCompraRequest simulacaoCompraRequest  = new SimulacaoCompraRequest();
        simulacaoCompraRequest.setCondicaoPagamento(createCondicaoPagamento());
        simulacaoCompraRequest.setProduto(createProduto());

        return simulacaoCompraRequest;
    }

    private TaxaJuro [] createTaxaJuros() {

        TaxaJuro taxaJuro =new TaxaJuro();
        taxaJuro.setData(new Date());
        //meio porcento
        taxaJuro.setValor(new BigDecimal("0.5"));

        TaxaJuro taxaJuros [] = {taxaJuro};
        return taxaJuros;
    }
    private CondicaoPagamento createCondicaoPagamento() {
        CondicaoPagamento condicaoPagamento =  new CondicaoPagamento();
        condicaoPagamento.setQtdeParcelas(5);
        condicaoPagamento.setValorEntrada(new BigDecimal("100"));
        return condicaoPagamento;
    }

    private Produto createProduto() {
        Produto produto =new Produto();
        produto.setCodigo(1L);
        produto.setNome("tv");
        produto.setValor(new BigDecimal("1100"));
        return produto;
    }
}
