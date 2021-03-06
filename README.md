# Desafio Android

Objetivo deste desafio é verificar o mínimo de conhecimento técnico na plataforma Android.

### Setup

Cadastre-se de forma gratuita no site: "https://developer.marvel.com/" para obter uma chave pública e privada.
> Utilize a documentação do site para obter as informações necessárias para o consumo das APIs.

## O desafio terá 3 telas:

### 1. - Listagem de personagens:

* Faça o consumo da API de listagem de personagens: "/v1/public/characters";
* Exiba o nome e foto de cada personagem;
* Ao selecionar o personagem, deverá direcionar para a tela de detalhes.

### 2. - Detalhes do Personagem:

* Exiba a imagem do personagem, o nome, a descrição e um botão de direcionamento para a uma tela que mostre qual a HQ mais cara daquele personagem.

### 3. - Detalhe da HQ mais cara do personagem:

* Faça o consumo da API de listagem de HQs por personagem: "/v1/public/characters/{characterId}/comics";
* Exiba na tela somente a revista mais cara daquele personagem com imagem, título, descrição e o preço.

***Regras:***

* Escolha um pattern de arquitetura que achar mais adequada para um projeto de grande porte;
* Faça o tratamento dos possíveis erros das APIs;
* Aplique testes unitários;
* Faça a paginação limitando a 20 itens por página;
* Os campos de texto devem ter no máximo 3 linhas.

***Bônus:***

* Kotlin
* Koin
* Retrofit
* Coroutines
* Constrant Layout
* MVI

### Regras para entrega do projeto:

* O projeto deve ser criado com o nome do desafio (desafio-android), seu nome e último sobrenome separados por traço. Ex: desafio-android-alexnaldo
* Ao final do prazo, disponibilize o link (Github, Gitlab, Bitbucket...) via e-mail para clone do projeto.