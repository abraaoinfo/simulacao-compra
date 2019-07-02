package com.viavarejo.simulacao.compra.controller;

import com.viavarejo.simulacao.compra.model.Parcela;
import com.viavarejo.simulacao.compra.request.SimulacaoCompraRequest;
import com.viavarejo.simulacao.compra.service.SimulacaoCompraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api (value = "simulacao compra")
@RestController
@RequestMapping("/v1/simulacao/compras")
public class SimulacaoCompraApi {

    @Autowired
    private SimulacaoCompraService simulacaoCompraService;

    @PostMapping
    @ApiOperation(value = "Simulacao de Compra", response = List.class)
    public ResponseEntity<List<Parcela>> geraSimulacaoCompra(@Valid @RequestBody SimulacaoCompraRequest saleRequest) {
        log.info("Iniciando a simulação de compra produto {}", saleRequest.getProduto().getNome());

        return ResponseEntity.ok(simulacaoCompraService.geraSimulacaoCompra(saleRequest));
    }



}
