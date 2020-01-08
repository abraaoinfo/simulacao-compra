# simulacao-compra

Api permite simular compra


A aplicação disponibilizar endpoint


### API
- **criar simulacao de compra**  
    
    - **[+POST+]** - [http://localhost:8080/v1/simulacao/compras)
    
    

### Executar aplicação

mvn clean install
mvn spring-boot:run

### Payload de entrada. exemplo.
{ "produto": { "codigo": 126, "nome": "Nome do Produto", "valor": 1000}, "condicaoPagamento": { "valorEntrada": 1, "qtdeParcelas": 13 } }




Obs. Não é necessario para executar a aplicação, mas é recomendado instalar o puglin do lombok na idea.

## Tecnologias usadas.

- spring-boot
- java 8
- lombok
- maven


## Divida Tecnicas

Não foram feito os teste intregado, apesar da sua importancia.





