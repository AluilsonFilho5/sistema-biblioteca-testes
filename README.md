Sistema de Empréstimo de Biblioteca

Descrição

Este projeto foi desenvolvido em Java com o objetivo de simular, de forma simples, o funcionamento de um sistema de empréstimo de livros em uma biblioteca.

A ideia principal foi focar na implementação das regras de negócio e na criação de testes unitários automatizados, garantindo que todas as funcionalidades importantes fossem validadas corretamente.

Tecnologias Utilizadas

Java
JUnit 5
Maven
JaCoCo

Regras de Negócio Testadas

Durante o desenvolvimento, foram definidas e testadas as seguintes regras:

Um livro só pode ser emprestado se estiver disponível.
Um usuário não pode realizar empréstimos caso possua multa pendente.
Cada usuário pode ter no máximo 3 livros emprestados ao mesmo tempo.
Ao realizar um empréstimo com sucesso, o livro passa a ficar indisponível.
Ao devolver um livro, ele volta a ficar disponível para novos empréstimos.
Não é permitido devolver um livro que não esteja emprestado.
Caso a devolução ocorra com atraso, uma multa é aplicada ao usuário.
Se a devolução for realizada dentro do prazo, nenhuma multa é gerada.

Como executar o projeto

Para executar os testes, basta rodar o seguinte comando:

mvn test

Cobertura de Testes

Para gerar o relatório de cobertura de testes, utilize:

mvn clean test

O relatório será gerado no caminho:

target/site/jacoco/index.html
