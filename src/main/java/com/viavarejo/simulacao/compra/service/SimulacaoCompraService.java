package com.viavarejo.simulacao.compra.service;

import com.viavarejo.simulacao.compra.exception.ServiceTaxaSelicException;
import com.viavarejo.simulacao.compra.model.*;
import com.viavarejo.simulacao.compra.request.SimulacaoCompraRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class SimulacaoCompraService {

   @Autowired
   private RestTemplate restTemplate;

   @Value("${servico.taxa-juros.uri}")
   private  String uriServiceTaxaSelic;


   public List<Parcela> geraSimulacaoCompra(SimulacaoCompraRequest vendaRequest) {

      validadeRequest(vendaRequest);

      BigDecimal txaJuroAcumulada = BigDecimal.ZERO;
      BigDecimal valorSerPago = calculaValorSerPago(vendaRequest);

      if(vendaRequest.getCondicaoPagamento().getQtdeParcelas() > 6) {
         txaJuroAcumulada = taxaJuroAcumuladoTrintaDias();
      }

      CarnePagamento carne = new CarnePagamento(
              vendaRequest.getCondicaoPagamento().getQtdeParcelas(), valorSerPago,  txaJuroAcumulada);


      log.info("simulacao de compra do produto finalizada {}", vendaRequest.getProduto().getNome());

      return carne.getParcelas();
   }

   private BigDecimal calculaValorSerPago(SimulacaoCompraRequest vendaRequest){
      return vendaRequest.getProduto()
              .getValor()
              .subtract(vendaRequest.getCondicaoPagamento().getValorEntrada());
   }


   private BigDecimal taxaJuroAcumuladoTrintaDias(){

      int trinaDias = 30;
      List<TaxaJuro> taxasSelic = getListTaxaSelicPorUltimoDias(trinaDias);

      return taxasSelic.stream()
              .map(TaxaJuro::getValor)
              .reduce(BigDecimal.ZERO,
                      BigDecimal::add).setScale(2, RoundingMode.HALF_EVEN);
   }

   private List<TaxaJuro> getListTaxaSelicPorUltimoDias(Integer ultimosDias) {
      try {

         TaxaJuro [] taxaJuros =restTemplate.getForObject(criandoUriTaxaSelicUltimoDias(ultimosDias),
                 TaxaJuro[].class);

         Assert.notEmpty(taxaJuros,"não_foi_possivel_encontrar_taxa_selic");

         return Arrays.asList(taxaJuros);
      }catch (Exception ex){
         log.error("erro_chamar_serviço_tava_juros", ex.getMessage());
         throw new ServiceTaxaSelicException("erro_chamar_serviço_tava_juros", ex);
      }

   }

   private void  validadeRequest(SimulacaoCompraRequest vendaRequest){

      Produto produto = vendaRequest.getProduto();
      CondicaoPagamento condicaoPagamento = vendaRequest.getCondicaoPagamento();

      Assert.isTrue( produto.getValor()
                      .compareTo( condicaoPagamento.getValorEntrada()) >= 0,
              "valor_entrada_maior_que_preço_produto");

   }

   private String criandoUriTaxaSelicUltimoDias(Integer ultimoDias) {
      return uriServiceTaxaSelic.concat(String.format("%s/%d", "ultimos", ultimoDias));
   }

}
