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
 *
 * @author Leandro
 */
public class AlgoritmoGenetico {

    private int tamanhoPopulacao;
    private List<Individuo> populacao = new ArrayList<>();// amrmaza dentro da lista todos os individuos da população
    private int geracao;
    private Individuo melhorSolucao;
    private List melhoresCromossomos = new ArrayList<>();

    public AlgoritmoGenetico(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    //metodo para inicializar a população
    public void inicializaPopulacao(List espacos, List valores, Double limiteEspacos) {
        for (int i = 0; i < tamanhoPopulacao; i++) {
            this.populacao.add(new Individuo(espacos, valores, limiteEspacos));
        }
        this.melhorSolucao = this.populacao.get(0);
    }

    public void ordenaPopulacao() {
        Collections.sort(this.populacao);
    }

    public void melhorIndividuo(Individuo individuo) {// verifica quem é o melhor individuo da população
        if (individuo.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()) {// atualiza o melhor individuo
            //se o individuo passado como parametro tiver uma melhor nota que o outro, ele assume como melhor individuo
            this.melhorSolucao = individuo;
        }
    }

    public Double somaAvalicao() {
        Double soma = 0.0;

        for (Individuo individuo : this.populacao) {
            soma += individuo.getNotaAvaliacao();
        }
        return soma;
    }

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

    public void visualizaGeracao() {
        Individuo melhor = this.populacao.get(0);//pega o melhor individuo, presumindo que ja se tenha feito a ordenação da população
        this.melhoresCromossomos.add(melhor);//adiciona o melhor individio a lista melhorCromossomos
        
        System.out.println("G " + this.populacao.get(0).getGeracao()
                + "\nValor " + melhor.getNotaAvaliacao()
                + "\nEspaço " + melhor.getEspacoUsado()
                + "\nCromossomo " + melhor.getCromossomo());

    }

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

    public List getMelhoresCromossomos() {
        return melhoresCromossomos;
    }

    public void setMelhoresCromossomos(List melhoresCromossomos) {
        this.melhoresCromossomos = melhoresCromossomos;
    }
     
    public int getTamanhoPopulacao() {
        return tamanhoPopulacao;
    }

    public void setTamanhoPopulacao(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    public List<Individuo> getPopulacao() {
        return populacao;
    }

    public void setPopulacao(List<Individuo> populacao) {
        this.populacao = populacao;
    }

    public int getGeracao() {
        return geracao;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

}
