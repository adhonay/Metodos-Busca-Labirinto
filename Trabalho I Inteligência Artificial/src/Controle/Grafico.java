package Controle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import Modelo.Celula;
import Modelo.Grau;
import Modelo.Aresta;

/**
 * Classe para modelagem de um grafo com lista de adjacencias.
 *
 * @author Isync
 */
public class Grafico {

    private final int BRANCO = -1;
    private final int CINZA = 1;
    private final int VERMELHO = 2;

    private final List<Celula> Grafico;
    private final List<Grau> solution;
    private final List<Grau> Graus;

    private int numVertices, numArestas, distancia;
    public long executionTime;

    //comparacao de distancia entre dois nos
    private final Comparator<Celula> distanciaComparator = new Comparator<Celula>() {
        @Override
        public int compare(Celula n1, Celula n2) {
            return Double.compare(n1.distancia + n1.heuristica, n2.distancia + n2.heuristica);
        }
    };

    /**
     * Construtor padrao. Faz inicializacos dos atributos.
     */
    public Grafico() {
        Grafico = new ArrayList<>();
        numVertices = numArestas = 0;
        solution = new ArrayList<>();
        Graus = new ArrayList<>();
    }

    /**
     * Novo vertice adicionado na lista
     *
     * @param x 
     * @param y 
     * @param pos 
     */
    public void addVertex(int x, int y, int pos) {
        Celula temp = new Celula(x, y, pos);
        Grafico.add(temp);
        numVertices++;
    }

    /**
     * Adiciona uma aresta 
     *
     * @param e Aresta a ser adicionada
     */
    public void addAresta(Aresta e) {
        Grafico.get(e.raiz).adjacente.add(e);
        numArestas++;
    }

    /**
     * Mostra o grafo
     */
    public void showGrafico() {
        int numAdj;
        List<Aresta> adjacente;

        //para todo vertice
        for (int i = 0; i < Grafico.size() ; i++) {
            numAdj = Grafico.get(i).adjacente.size();
            adjacente = Grafico.get(i).adjacente;

            System.out.printf("(%d) .. [%d;%d]", i, Grafico.get(i).x, Grafico.get(i).y);

            for (int j = 0; j < numAdj; j++) {
                System.out.printf(" -> [%d] (%d,%d)", Grafico.get(i).adjacente.get(j).distancia, Grafico.get(Grafico.get(i).adjacente.get(j).distancia).x, Grafico.get(Grafico.get(i).adjacente.get(j).distancia).y);
            }

            System.out.println("");
        }
    }

    /**
     * Faz a busca em largura
     *
     * @param begin Vertice v1
     * @param end Vertice v2
     */
    public void buscaLarg(int begin, int end) {
        long startTime = System.currentTimeMillis();        
        distancia = 0;

        solution.clear();
        Graus.clear();

        //inicializacoes dos vertices com branco
        for (int i = 0; i < Grafico.size(); i++) {
            Grafico.get(i).cor = BRANCO;
        }

        List<Celula> fila = new ArrayList<>();
        fila.add(Grafico.get(begin));
        Grafico.get(begin).pai = -1;
        Grau stp;
        Celula mn;

        //enquanto a fila contem vertices
        while (!fila.isEmpty()) {
            //faz a retirada de um vertice da fila
            mn = fila.remove(0);
            stp = new Grau(mn.pai, mn.pos);
            Graus.add(stp);

            //para execucao se o vertice ja for o final
            if (mn.pos == end) {
                break;
            } 
            else {
                //expande para os vertices adjacentes
                for (int i = 0; i < mn.adjacente.size(); i++) {

                    if (Grafico.get(mn.adjacente.get(i).distancia).cor == BRANCO) {

                        Grafico.get(mn.adjacente.get(i).distancia).pai = mn.pos;

                        Grafico.get(mn.adjacente.get(i).distancia).cor = CINZA;

                        fila.add(Grafico.get(mn.adjacente.get(i).distancia));
                    }
                }

                Grafico.get(mn.pos).cor = VERMELHO;
            }
        }

        Graus.remove(0);

        gerarSolucao(end);

        executionTime = System.currentTimeMillis() - startTime;
    }

    /**
     * Faz a busca em profundidade
     * @param begin
     * @param end
     */
    public void buscaProf(int begin, int end) {
        long startTime = System.currentTimeMillis();
        distancia = 0;

        solution.clear();
        Graus.clear();
        //inicializacoes dos vertices com branco

        for (int i = 0; i < Grafico.size(); i++) {
            Grafico.get(i).cor = BRANCO;
        }

        Celula mn = Grafico.get(begin);
        Grafico.get(begin).pai = -1;
        visita(mn, end);
        
        gerarSolucao(end);
        
        executionTime = System.currentTimeMillis() - startTime;
        System.out.println(begin);
        System.out.println(end);
    }

    /**
     * Expande os nos para fazer a busca em profundidade
     * @param celula 
     * @return true se chegou ao destino
     */
    private boolean visita(Celula celula, int end) {
        boolean find = false;
        Grau stp;

        if (celula.pos == end) {
            find = true;
        } else {

            Grafico.get(celula.pos).cor = CINZA;
            for (int i = 0; i < celula.adjacente.size() && !find; i++) {

                if (Grafico.get(celula.adjacente.get(i).distancia).cor == BRANCO) {
                    stp = new Grau(celula.pos, celula.adjacente.get(i).distancia);
                    Graus.add(stp);
                    find = visita(Grafico.get(celula.adjacente.get(i).distancia), end);
                    Grafico.get(celula.adjacente.get(i).distancia).pai = celula.pos;
                }
            }
        }

        Grafico.get(celula.pos).cor = VERMELHO;

        return find;
    }

    /**
     * Faz a busca por algoritmo guloso
     * @param begin Vertice v1
     * @param end Vertice v2
     * @param heuristic tipo da euristica
     */
    public void greedy(int begin, int end, int heuristic) {
        long startTime = System.currentTimeMillis();
        distancia = 0;

        solution.clear();
        Graus.clear();

        //inicializa todos com branco
        for (int i = 0; i < Grafico.size(); i++) {
            Grafico.get(i).cor = BRANCO;
        }

        //variaveis do metodo
        PriorityQueue<Celula> fila = new PriorityQueue<>(numVertices, distanciaComparator);

        Grau stp;
        Celula v;


        //calcular heuriticas
        iniciarHeuristica(heuristic, end);
        defineDistanciaEuclidiana(0);

        Grafico.get(begin).distancia = 0;
        Grafico.get(begin).pai = -1;
        fila.add(Grafico.get(begin));

        //ennquanto xistirem vertices na fila
        while (!fila.isEmpty()) {
            Celula u = fila.poll();
            
            stp = new Grau(u.pai, u.pos);
            Graus.add(stp);

            //vertice final. para.
            if (u.pos == end) {
                break;
            } 
            else {
                //adiciona vertices na fila. considera so heuristica
                for (int i = 0; i < u.adjacente.size(); i++) {
                    v = Grafico.get(u.adjacente.get(i).distancia);
                    if (v.cor == BRANCO) {
                        v.pai = u.pos;
                        v.cor = CINZA;
                        fila.add(v);
                    }
                }
            }

            u.cor = VERMELHO;
        }

        Graus.remove(0);
        gerarSolucao(end);
        executionTime = System.currentTimeMillis() - startTime;
    }
/**
 * Faz busca por algoritmo a*
 * @param begin
 * @param end
 * @param heuristic 
 */
    public void aStar(int begin, int end, int heuristic) {
        long startTime = System.currentTimeMillis();
        distancia = 0;

        solution.clear();
        Graus.clear();

        for (int i = 0; i < Grafico.size(); i++) {
            Grafico.get(i).cor = BRANCO;
        }

        PriorityQueue<Celula> fila = new PriorityQueue<>(numVertices, distanciaComparator);
        Grau stp;
        Celula v;

  
        //inicializa as heiristicas
        iniciarHeuristica(heuristic, end);
        defineDistanciaEuclidiana(Double.MAX_VALUE);

        Grafico.get(begin).distancia = 0;
        Grafico.get(begin).pai = -1;
        fila.add(Grafico.get(begin));

        while (!fila.isEmpty()) {
            Celula u = fila.poll();

            stp = new Grau(u.pai, u.pos);
            Graus.add(stp);

            if (u.pos == end) {
                break;
            } 
            else {
                //adiciona na fila, considerando g(n)+ h(n)
                for (int i = 0; i < u.adjacente.size(); i++) {
                    v = Grafico.get(u.adjacente.get(i).distancia);
                    if (v.cor == BRANCO) {
                        v.distancia = u.distancia + u.adjacente.get(i).peso;
                        v.pai = u.pos;
                        v.cor = CINZA;
                        fila.add(v);
                    } 
                    else if (v.distancia > u.distancia + u.adjacente.get(i).peso && fila.contains(v)) {
                        fila.remove(v);
                        v.distancia = u.distancia + u.adjacente.get(i).peso;
                        v.pai = u.pos;
                        fila.add(v);
                    }
                }
            }

            u.cor = VERMELHO;
        }

        Graus.remove(0);
        gerarSolucao(end);
        executionTime = System.currentTimeMillis() - startTime;
    }

    private void gerarSolucao(int end) {
        Celula mn = Grafico.get(end);
        Grau stp;
        while (mn.pai != -1) {
            stp = new Grau(mn.pai, mn.pos);
            solution.add(0, stp);
            distancia += Distancia.DistanciaManhat(mn.x, mn.y, Grafico.get(mn.pai).x, Grafico.get(mn.pai).y);
            mn = Grafico.get(mn.pai);

        }
    }

    
    private void iniciarHeuristica(int heuristic, int end) {

        Celula n, f = Grafico.get(end);
        heuristic = 1;
        switch (heuristic) {

            case Distancia.Distancia_Euclid:
                for (int i = 0; i < Grafico.size(); i++) {
                    n = Grafico.get(i);
                    n.heuristica = Distancia.DistanciaEuclid(n.x, n.y, f.x, f.y);
                }
                break;
        }
    }

    private void defineDistanciaEuclidiana(double value) {
        Celula n;
        for (int i = 0; i < Grafico.size(); i++) {
            n = Grafico.get(i);
            n.distancia = value;
        }
    }

    public int getDistance() {
        return this.distancia;
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public int getNumArestas() {
        return this.numArestas;
    }

    public Celula getCelula(int pos) {
        Celula mn = null;

        if (pos >= 0 && pos < Grafico.size()) {
            mn = Grafico.get(pos);
        }

        return mn;
    }

    public List<Grau> getSolution() {
        return this.solution;
    }

    public List<Grau> getGraus() {
        return this.Graus;
    }

    public Celula getVertex(int i) {
        return Grafico.get(i);
    }

}
