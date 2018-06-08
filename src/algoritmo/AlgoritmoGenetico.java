/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public class AlgoritmoGenetico {

    private int tamanhoPopulacao;
    private List<Individuo> populacao = new ArrayList<>();// armazena dentro da lista todos os individuos da população
    private int geracao;
    private Individuo melhorSolucao;
    private List melhoresCromossomos = new ArrayList<>();

    /**
     *
     * @param tamanhoPopulacao
     */
    public AlgoritmoGenetico(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    /**
     * Método para inicializar a população
     *
     * @param espacos
     * @param valores
     * @param limiteEspacos
     */
    public void inicializaPopulacao(List espacos, List valores, Double limiteEspacos) {
        for (int i = 0; i < tamanhoPopulacao; i++) {
            this.populacao.add(new Individuo(espacos, valores, limiteEspacos));
        }
        this.melhorSolucao = this.populacao.get(0);
    }

    /**
     * Método para ordenar a população
     */
    public void ordenaPopulacao() {
        Collections.sort(this.populacao);
    }

    /**
     * Método que verifica quem é o melhor individuo da população atualiza o
     * melhor individuo se o individuo passado como parametro tiver uma melhor
     * nota que o outro, ele assume como melhor individuo
     *
     * @param individuo
     */
    public void melhorIndividuo(Individuo individuo) {
        if (individuo.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()) {
            this.melhorSolucao = individuo;
        }
    }

    /**
     * Método que retorna a soma avaliação
     *
     * @return
     */
    public Double somaAvalicao() {
        Double soma = 0.0;

        for (Individuo individuo : this.populacao) {
            soma += individuo.getNotaAvaliacao();
        }
        return soma;
    }

    /**
     * @param somaAvalicao
     * @return
     */
    public int selcionaPai(Double somaAvalicao) {
        int pai = -1;// retorna um valor invalido, usado apenas  pra inicializar a variavel

        Double valorSorteado = Math.random() * somaAvalicao;
        Double soma = 0.0;
        int i = 0;

        while (i < this.populacao.size() && soma < valorSorteado) {
            soma += this.populacao.get(i).getNotaAvaliacao();
            pai += 1;
            i += 1;
        }
        return pai;
    }

    /**
     * Método visualiza geração pega o melhor individuo, presumindo que ja se
     * tenha feito a ordenação da população adiciona o melhor individio a lista
     * melhorCromossomos
     */
    public void visualizaGeracao() {
        Individuo melhor = this.populacao.get(0);
        this.melhoresCromossomos.add(melhor);

        System.out.println("G " + this.populacao.get(0).getGeracao()
                + "\nValor " + melhor.getNotaAvaliacao()
                + "\nEspaço " + melhor.getEspacoUsado()
                + "\nCromossomo " + melhor.getCromossomo());

    }

    /**
     *
     * @param taxaMutacao
     * @param numeroGeracoes
     * @param espacos
     * @param valores
     * @param limiteespacos
     * @return
     */
    public List resolver(Double taxaMutacao, int numeroGeracoes, List espacos, List valores,
            Double limiteespacos) {

        this.inicializaPopulacao(espacos, valores, limiteespacos);
        //inicia a avaliação
        for (Individuo individuo : this.populacao) {
            individuo.avaliacao();
        }
        this.ordenaPopulacao();
        this.visualizaGeracao();

        //criterio de parada
        for (int geracao = 0; geracao < numeroGeracoes; geracao++) {
            Double somaAvalicao = this.somaAvalicao();
            List<Individuo> novaPopulacao = new ArrayList<>();

            for (int i = 0; i < this.populacao.size() / 2; i++) {
                int pai1 = this.selcionaPai(somaAvalicao);
                int pai2 = this.selcionaPai(somaAvalicao);

                List<Individuo> filhos = this.populacao.get(pai1).crossover(this.populacao.get(pai2));
                novaPopulacao.add(filhos.get(0).mutacao(taxaMutacao));
                novaPopulacao.add(filhos.get(1).mutacao(taxaMutacao));
            }
            // seta a nova populaação
            this.setPopulacao(novaPopulacao);
            // for para avaliar a nova população
            for (Individuo individuo : this.getPopulacao()) {
                individuo.avaliacao();
            }
            this.ordenaPopulacao();
            this.visualizaGeracao();
            Individuo melhor = this.populacao.get(0);
            this.melhorIndividuo(melhor);//atualiza o melhor individuo

        }
        System.out.println("Melhor solução G : " + this.melhorSolucao.getGeracao()
                + //mostra em que geração estava o melhor
                "\nValor : " + this.melhorSolucao.getNotaAvaliacao()
                +//pega a melhor nota
                "\nEspaco : " + this.melhorSolucao.getEspacoUsado()
                + "\nCromossomo : " + this.melhorSolucao.getCromossomo());

        return this.melhorSolucao.getCromossomo();
    }

    /**
     *
     * @return
     */
    public List getMelhoresCromossomos() {
        return melhoresCromossomos;
    }

    /**
     *
     * @param melhoresCromossomos
     */
    public void setMelhoresCromossomos(List melhoresCromossomos) {
        this.melhoresCromossomos = melhoresCromossomos;
    }

    /**
     *
     * @return
     */
    public int getTamanhoPopulacao() {
        return tamanhoPopulacao;
    }

    /**
     *
     * @param tamanhoPopulacao
     */
    public void setTamanhoPopulacao(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    /**
     *
     * @return
     */
    public List<Individuo> getPopulacao() {
        return populacao;
    }

    /**
     *
     * @param populacao
     */
    public void setPopulacao(List<Individuo> populacao) {
        this.populacao = populacao;
    }

    /**
     *
     * @return
     */
    public int getGeracao() {
        return geracao;
    }

    /**
     *
     * @param geracao
     */
    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    /**
     *
     * @return
     */
    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    /**
     *
     * @param melhorSolucao
     */
    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

}
