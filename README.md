# Ownership Matrice
Geração de matrizes que indicam a  a relação de responsabilidade entre os módulos de um sistema e os principais contribuidores de cada um.

## Descrição
Esta ferramenta utiliza como entrada bases de dados em CSV geradas pelo framework [RepoDriller](https://github.com/mauricioaniche/repodriller). A partir desses dados, a ferramenta é capaz de automaticamente detectar os principais módulos da base de dados, definir a responsabilidade de cada módulo para cada desenvolvedor e gerar uma matriz contendo a relação desses dados, bastando apenas informar o nome do arquivo de saída e o separador dos valores.

## Instalação e uso
Clone o repositório e acesse o diretório `ownership-matrice`.

```
:~$ git clone https://github.com/carlasanches/ownership-matrice
:~$ cd ownership-matrice
```

Por padrão, o código vem configurado com o exemplo da base de dados mockito.csv. Para testar, basta compilar e executar o código-fonte, que irá gerar uma matriz no arquivo `exemplo.csv` dentro do diretório principal. Assim que o arquivo CSV é gerado, o programa exibe a mensagem "escrito com sucesso".

```
:~$ javac src/*.java
:~$ java src.Main
```

Dentro do diretório `exemplos/resultados-repodriller`, encontram-se outros exemplos de bases de dados de repositórios minerados pelo framework [RepoDriller](https://github.com/mauricioaniche/repodriller). Para utilizar os exemplos ou bases de dados próprias, basta seguir os seguintes passos:

1. Abra o diretório `ownership-matrice/src`
2. Abra o arquivo `Main.java` em um editor de texto
3. Altere o diretório de entrada na função `Recursos.getInstance().criarRecursosRepoDriller()`. Exemplo:

```
Recursos.getInstance().criarRecursosRepoDriller("../ownership-matrice/exemplos/resultados-repodriller/mockito.csv");
```

Para alterar o nome do arquivo de saída, basta alterar a função `mom.save()`:

```
mom.save("exemplo.csv");
```

Após as alterações, siga novamente os passos para compilar e executar o programa.
