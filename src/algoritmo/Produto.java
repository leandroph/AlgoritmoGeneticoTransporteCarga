/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

/**
 * Aplicação de algoritmos genéticos aplicados na logística. Melhor ocupação da
 * capacidade de carga do veículo de transporte.
 *
 * Trabalho avaliativo da disciplina de Inteligência Artificial Ciência da
 * Computação - UNIJUÍ - 1º Semestre/2018
 *
 * @author Cristiano Künas
 * @author Elison Christoph
 * @author Leandro Heck
 */
public class Produto {

    private String nome;
    private Double espaco;
    private Double valor;

    /**
     * Construtor da classe Produto
     *
     * @param nome
     * @param espaco
     * @param valor
     */
    public Produto(String nome, Double espaco, Double valor) {
        this.nome = nome;
        this.espaco = espaco;
        this.valor = valor;
    }

    /**
     * Método que retorna o nome do produto.
     *
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para atribuir nome ao produto.
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que retorna o espaço utilizado pelo produto.
     *
     * @return espaço
     */
    public Double getEspaco() {
        return espaco;
    }

    /**
     * Método que atribui o espaço que o produto irá utilizar..
     *
     * @param espaco
     */
    public void setEspaco(Double espaco) {
        this.espaco = espaco;
    }

    /**
     * Método que retorna o valor do produto.
     *
     * @return valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Método que atribui o valor para o produto.
     *
     * @param valor
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }
}
