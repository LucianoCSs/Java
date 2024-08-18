package br.com.crudjava.DAO;

/* Os Records são uma nova forma de declarar classes no Java. 
Eles são imutáveis por padrão, o que significa que não podemos 
alterar seus valores após a criação do objeto. Além disso, 
eles são bastante úteis para representar dados, como, por exemplo, 
uma entidade de banco de dados.*/

public record Produto(Long id, String nome, Integer quantidade, Double valor) {
    public Produto(String nome, Integer quantidade, Double valor){
        this(null, nome, quantidade, valor);
    }
}
