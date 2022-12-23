# mini-autorizador

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger

Para utilização do projeto é necessário estar com o Maven e uma JDK devidamente instalada e configurado no ambiente.
Com os requisitos instalados rodar o seguinte camando na pasta raiz do projeto:
```sh
mvn clean install
```
Após a conclusão do comando anterior rodar o ambiente do docker:
```sh
docker-compose up --build
```
Perfeito, agora o docker já está rodando juntamente com o banco de dados mysql, agora será necessário conectar ao banco de dados e criar a tabela de cartao, a query se encontra na pasta do projeto em "resource" -> "mysql_queries".

Pronto! Agora é só baixar a coleção dos endpoint abaixo:
[mini-autorizador.postman_collection.zip](https://github.com/PauloPrass/mini-autorizador/files/10297673/mini-autorizador.postman_collection.zip)
