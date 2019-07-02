# Sistema de Simulaçao de Compra

O objetivo dessa aplicação é simular uma compra.

O sistema recebe informação do produto e da condição de pagamento e realiza a simulação.
O Sistema pode consulta a taxa selic no site do governo caso o parcelamento seja acima de 6.

# Exemplo de entrada de dados

{  
   "produto":{  
      "codigo": 1,
      "nome":"produto",
      "valor":1300
   },
   "condicaoPagamento":{  
      "valorEntrada":300,
      "qtdeParcelas":8
   }
}

# Exemplo de Saida para um taxa de juro de menos de 1% 

--Valor taxa de juros 0.74.
[
    {
        "numParcela": 1,
        "valor": 125.92,
        "taxaJuros": 0.74
    },
    {
        "numParcela": 2,
        "valor": 125.92,
        "taxaJuros": 0.74
    },
    {
        "numParcela": 3,
        "valor": 125.92,
        "taxaJuros": 0.74
    },
    {
        "numParcela": 4,
        "valor": 125.92,
        "taxaJuros": 0.74
    },
    {
        "numParcela": 5,
        "valor": 125.92,
        "taxaJuros": 0.74
    },
    {
        "numParcela": 6,
        "valor": 125.92,
        "taxaJuros": 0.74
    },
    {
        "numParcela": 7,
        "valor": 125.92,
        "taxaJuros": 0.74
    },
    {
        "numParcela": 8,
        "valor": 125.92,
        "taxaJuros": 0.74
    }
]

A aplicação disponibiliza alguns endpoints 

 - Informação sobre o sistemas /actuator 

 - Simular Comprar /simulacao/compras
 
 ### Tecnologias Usadas
  - java 8
  - Maven
  - swagger
  - Mockito
  - spring boot
  - RestTemple

### Ambiente para executar a aplicação 
 - java 8
 - Maven
 
 ##
### comandor para rodar aplicação.
 - mvn spring-boot:run
 - Executar classe SimulacaoCompraApplication 
 
 ### comandor para rodar aplicação.

### API

 - **Simulacão De Compra**

    - **[+POST+]** -  [http://localhost:8080/v1/simulacao/compras](http://localhost:8080/v1/simulacao/compras)
    
 - ****

    - **[+GET+]** -  [http://localhost:8080/actuator](http://localhost:8080/actuator)    
    

    

    
