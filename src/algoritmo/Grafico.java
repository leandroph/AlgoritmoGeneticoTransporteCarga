/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo;

import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.Line;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

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
public class Grafico extends ApplicationFrame{//cria uma janela para a visualização do gráfico
    
    private List<Individuo> melhoresCromossomos = new ArrayList<>();
    
    /**
     * Método que define o gráfico
     * 
     * @param tituloJanela
     * @param tituloGrafico
     * @param melhores 
     */
    public Grafico(String tituloJanela, String tituloGrafico, List melhores){
        super(tituloJanela);
       
        this.melhoresCromossomos = melhores;
        JFreeChart graficoLinha = ChartFactory.createLineChart(tituloGrafico, 
                "Geração", "Valor", 
                carregarDados(), 
                PlotOrientation.VERTICAL, 
                true, true, false);
        
       /**
        * aumenta o tamanho da linha no gráfico
        */
        CategoryPlot plot = (CategoryPlot) graficoLinha.getPlot();
        LineAndShapeRenderer render = (LineAndShapeRenderer) plot.getRenderer();
        render.setSeriesStroke(0, new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0.0f, new float[]{1.0f,1.0f}, 0.0f));
       //render.setSeriesShapesVisible(0, true);
        //render.setSeriesItemLabelsVisible(0, true);//seta o valor da melhor solução da geração na linha do grafico
       
        
        ChartPanel janelaGrafico = new ChartPanel(graficoLinha);
        janelaGrafico.setPreferredSize(new java.awt.Dimension(800,600));//cria a dimenção da janela
        setContentPane(janelaGrafico);
    }
    
    /**
     * Método para carregar dados
     * @return dados
     */
    private DefaultCategoryDataset carregarDados(){//usado para mostrar os resultados em um gráfico
        DefaultCategoryDataset dados = new DefaultCategoryDataset();
        for (int i = 0; i < melhoresCromossomos.size(); i++) {
            dados.addValue(melhoresCromossomos.get(i).getNotaAvaliacao(), "Melhor solução", "" + i);
        }
        return dados; // essa função percorre os cromossomos e pega a nota de avaliação de cada um 
        // e coloca no padrao DefaultCategoryDataset, que é usado por esse blioteca
    }
}
