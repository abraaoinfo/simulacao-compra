package com.viavarejo.simulacao.compra.repository;

import com.viavarejo.simulacao.compra.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
}
