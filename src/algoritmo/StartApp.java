package algoritmo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import org.jfree.ui.RefineryUtilities;

/**
 * Aplicação de algoritmos genéticos aplicados na logística. Melhor ocupação
 * da capacidade de carga do veículo de transporte.
 * 
 * Trabalho avaliativo da disciplina de Inteligência Artificial
 * Ciência da Computação - UNIJUÍ - 1º Semestre/2018
 * 
 * @author Cristiano Künas
 * @author Elison Christoph
 * @author Leandro Heck
 */
public class StartApp {

    /**
     * Método main para execução da aplicação
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // cria uma lista de Produto que vai receber todos os produtos
        List<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto("Geladeira Dako", 0.751, 999.90));
        listaProdutos.add(new Produto("Geladeira Dako", 0.751, 999.90));
        listaProdutos.add(new Produto("IPhone 6", 0.0000899, 2199.12));
        listaProdutos.add(new Produto("IPhone 6", 0.0000899, 2199.12));
        listaProdutos.add(new Produto("IPhone 6", 0.0000899, 2199.12));
        listaProdutos.add(new Produto("IPhone 6", 0.0000899, 2199.12));
        listaProdutos.add(new Produto("TV 55'", 0.400, 4346.99));
        listaProdutos.add(new Produto("TV 55'", 0.400, 4346.99));
        listaProdutos.add(new Produto("TV 55'", 0.400, 4346.99));
        listaProdutos.add(new Produto("TV 50'", 0.290, 3999.90));
        listaProdutos.add(new Produto("TV 50'", 0.290, 3999.90));
        listaProdutos.add(new Produto("TV 50'", 0.290, 3999.90));
        listaProdutos.add(new Produto("TV 42'", 0.200, 2999.90));
        listaProdutos.add(new Produto("TV 42'", 0.200, 2999.90));
        listaProdutos.add(new Produto("TV 42'", 0.200, 2999.90));
        listaProdutos.add(new Produto("Notebook Dell", 0.00350, 2499.90));
        listaProdutos.add(new Produto("Notebook Dell", 0.00350, 2499.90));
        listaProdutos.add(new Produto("Notebook Dell", 0.00350, 2499.90));
        listaProdutos.add(new Produto("Ventilador Panasonic", 0.496, 199.90));
        listaProdutos.add(new Produto("Ventilador Panasonic", 0.496, 199.90));
        listaProdutos.add(new Produto("Microondas Eletrolux", 0.0424, 308.66));
        listaProdutos.add(new Produto("Microondas LG", 0.0544, 429.90));
        listaProdutos.add(new Produto("Microondas LG", 0.0544, 429.90));
        listaProdutos.add(new Produto("Microondas LG", 0.0544, 429.90));
        listaProdutos.add(new Produto("Microondas Panasonic", 0.0319, 299.29));
        listaProdutos.add(new Produto("Geladeira Brastemp", 0.635, 849.00));
        listaProdutos.add(new Produto("Geladeira Consul", 0.870, 1199.89));
        listaProdutos.add(new Produto("Geladeira Consul", 0.870, 1199.89));
        listaProdutos.add(new Produto("Notebook Lenovo", 0.498, 1999.90));
        listaProdutos.add(new Produto("Notebook Lenovo", 0.498, 1999.90));
        listaProdutos.add(new Produto("Notebook Lenovo", 0.498, 1999.90));
        listaProdutos.add(new Produto("Notebook Lenovo", 0.498, 1999.90));
        listaProdutos.add(new Produto("Notebook Asus", 0.527, 3999.00));
        listaProdutos.add(new Produto("Notebook Asus", 0.527, 3999.00));
        listaProdutos.add(new Produto("Notebook Asus", 0.527, 3999.00));//35 produtos

        //cria as lista para trabalhar com a classe Individuo
        List espacos = new ArrayList<>();
        List valores = new ArrayList<>();
        List nomes = new ArrayList<>();

        for (Produto produto : listaProdutos) {
            espacos.add(produto.getEspaco());// adiciona o valor do espaço do produto a variavel espaço
            valores.add(produto.getValor());// adiciona o valor do produto na varialvel valor
            nomes.add(produto.getNome());// adiociona o nome do produto  a variavel nome
        }

        Double limite = 10.0;//limite total da carga que seria de 3m³
        int tamanhoPopulacao = 40;
        Double taxaMutacao = 0.05;
        int numeroGeracoes = 100;//numero total do processo de evolução

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanhoPopulacao);
        ag.inicializaPopulacao(espacos, valores, limite);

        List resultado = ag.resolver(taxaMutacao, numeroGeracoes, espacos, valores, limite);
        //for para imprimir a listagem dos produtos
        for (int i = 0; i < listaProdutos.size(); i++) {
            if(resultado.get(i).equals("1")){
                System.out.println("Nome : " + listaProdutos.get(i).getNome());
            }
        }
        
        Grafico g = new Grafico("Algoritmo Genético", "Evolução das soluções", ag.getMelhoresCromossomos());
        g.pack();
        RefineryUtilities.centerFrameOnScreen(g);
        g.setVisible(true);

        
//        for (Individuo individuo : ag.getPopulacao()) {
//            individuo.avaliacao();//inicia a avalição de cada individuo da população
//        }
//
//        ag.ordenaPopulacao();
//        ag.melhorIndividuo(ag.getPopulacao().get(0));//pega na posição 0 pois após a ordenação o melhor individuo  vai estar na posição 0
//        Double soma = ag.somaAvalicao();// chama a soma de todas as avalições
//        Double probabilidadeMutacao = 0.01; // 1% de probabilidade de mutação
//        List<Individuo> novaPopulacao = new ArrayList<>();
//
//        for (int i = 0; i < ag.getPopulacao().size() / 2; i++) {//percorre cada um dos elementos da população, dividindo a população em dois
//            //a divisão é  feita pois o tamanho da população é 20, entao é pego apenas 10 individuos para a seleção a nova população
//            // senão haveria estouro no total da população
//            int pai1 = ag.selcionaPai(soma);
//            int pai2 = ag.selcionaPai(soma);
//            //Construção da nova população
//            List<Individuo> filhos = ag.getPopulacao().get(pai1).crossover(ag.getPopulacao().get(pai2));
//            novaPopulacao.add(filhos.get(0).mutacao(probabilidadeMutacao));
//            novaPopulacao.add(filhos.get(1).mutacao(probabilidadeMutacao));
//            //adiciona a nova população os dois novos filhos
//        }
//        ag.setPopulacao(novaPopulacao);//sobreescreve a antiga população e passa a valer a nova população
//        
//        //avalia a nova população
//        for (Individuo individuo : ag.getPopulacao()) {
//            individuo.avaliacao();
//        }
//        
//        ag.ordenaPopulacao();// ordena a nova população
//        ag.melhorIndividuo(ag.getPopulacao().get(0));// pega o melhor individuo que está na posição zero
//        soma = ag.somaAvalicao();
//        System.out.println("Melhor " + ag.getMelhorSolucao().getCromossomo() +
//        "\nValor " + ag.getMelhorSolucao().getNotaAvaliacao());


//       System.out.println("Soma das avaliações: " + soma);
//        System.out.println("Melhor solução para o problema: " + ag.getMelhorSolucao().getCromossomo() + "\n"
//                + "Nota: " + ag.getMelhorSolucao().getNotaAvaliacao()); 
//        for (int i = 0; i < ag.getTamanhoPopulacao(); i++) {
//            System.out.println("*** Individuo " + i + "****\n Espaços " + ag.getPopulacao().get(i).getEspacos() + 
//                    "\n Valores " + ag.getPopulacao().get(i).getValores() + 
//                    "\n Cromossomo " + ag.getPopulacao().get(i).getCromossomo() +
//                    "\n Nota " + ag.getPopulacao().get(i).getNotaAvaliacao());
//        }
//        Individuo individuo1 = new Individuo(espacos, valores, limite);
//        System.out.println("Espaços " + individuo1.getEspaços());
//        System.out.println("Valores " + individuo1.getValores());
//        System.out.println("Cromossomo " + individuo1.getCromossomo());
//        System.out.println("\n Componentes da carga:");
//
//        //for para imprimir o nome do objeto que vai ser levado na carga
//        for (int i = 0; i < listaProdutos.size(); i++) {
//            if (individuo1.getCromossomo().get(i) == "1") {
//                System.out.println("Nome " + listaProdutos.get(i).getNome()
//                        + " valor " + listaProdutos.get(i).getValor());
//            }
//        }
//        Individuo individuo1 = new Individuo(espacos, valores, limite);
//        System.out.println("Individuo 1 " + individuo1.getCromossomo());
//        individuo1.avaliacao();
//        System.out.println("Nota : " + individuo1.getNotaAvaliacao());
//        System.out.println("Espaço usado: " + individuo1.getEspacoUsado());
//        
//        Individuo individuo2 = new Individuo(espacos, valores, limite);
//        System.out.println("Individuo 2 " + individuo2.getCromossomo());
//        individuo2.avaliacao();
//        System.out.println("Nota : " + individuo2.getNotaAvaliacao());
//        System.out.println("Espaço usado: " + individuo2.getEspacoUsado());
//        
//        individuo1.crossover(individuo2);//passo os individuos que vao fazer o crossover
//        
//        individuo1.mutacao(0.05);// limite de 5 % de mutação
//        individuo2.mutacao(0.05);
    }

}
