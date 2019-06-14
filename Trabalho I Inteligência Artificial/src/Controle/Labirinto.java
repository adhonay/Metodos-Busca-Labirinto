package Controle;

import Tela.Main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Celula;
import Modelo.Grau;
import Modelo.Aresta;
import Tela.Painel;

/**
 * Classe responsavel por gerar e tratar o labirinto
 *
 * @author Isync
 */
public class Labirinto {

 
    public static final int MuretaCor = Color.white.getRGB();
    public static final int inicioCor = Color.blue.getRGB();
    public static final int fimCor = Color.red.getRGB();
    public static final int fundoCor = Color.black.getRGB();
    public static final int nivelCor = Color.yellow.getRGB();
    public static final int colorirVet = Color.magenta.getRGB();
    public static final float parametroProp = (float) 0.1;

    public static final int buscaLarg = 1;
    public static final int buscaProf = 2;
    public static final int guloso = 4;
    public static final int estrela = 5;
    
    private static final Random RDM = new Random();
    private static final float ProporcaoVet = (float) 0.5;

    private final int largura, altura, TamanhoBloco;
    private final double proporcao;
    private final Cell[][] cells;
    private final BufferedImage image;
    private final Grafico Grafico;

    private boolean resolvido;
    private int NoInicial;
    private int NoFinal;

    private int indiceVet;
    private BufferedImage GraficoView;

    public Labirinto(int largura , int altura, int blockSize, double randomproporcao) {
        this.largura = largura ;
        this.altura = altura;
        this.TamanhoBloco = blockSize;
        this.image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        this.cells = new Cell[ largura/ TamanhoBloco][altura / TamanhoBloco];
        this.proporcao = randomproporcao;
        this.indiceVet = 0;
        this.resolvido = false;

        //inicializa o vetor de celulas
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        generateMaze(cells[0][0], ' ');
        randomizeMaze();
        generateImage();
        Grafico = generateGrafico();
//        Grafico.showGrafico();
        GraficoView = null;
    }

    public void clearPath() {
        //limpa a solucao
        List<Grau> list = Grafico.getGraus();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    int middleStartX = (int) ((Grafico.getCelula(list.get(i).raiz).x + (1.0 / 2.0)) * TamanhoBloco);
                    int middleStartY = (int) ((Grafico.getCelula(list.get(i).raiz).y + (1.0 / 2.0)) * TamanhoBloco);
                    int middleEndX = (int) ((Grafico.getCelula(list.get(i).distancia).x + (1.0 / 2.0)) * TamanhoBloco);
                    int middleEndY = (int) ((Grafico.getCelula(list.get(i).distancia).y + (1.0 / 2.0)) * TamanhoBloco);

                    setPath(middleStartX, middleStartY, middleEndX, middleEndY, fundoCor, image);
                    setPath(middleStartX, middleStartY, middleEndX, middleEndY, fundoCor, GraficoView);
                    this.resolvido = false;
                } catch (Exception e) {
                }
            }
        }

        setBlockColor(0, 0, inicioCor, image);
        setBlockColor((largura / TamanhoBloco - 1) * TamanhoBloco, (altura / TamanhoBloco - 1) * TamanhoBloco, fimCor, image);

        setBlockColor(0, 0, inicioCor, GraficoView);
        setBlockColor((largura / TamanhoBloco - 1) * TamanhoBloco, (altura / TamanhoBloco - 1) * TamanhoBloco, fimCor, GraficoView);

        //pinta os vertices
        int numVertices = Grafico.getNumVertices();
        for (int i = 0; i < numVertices; i++) {
            Celula mn = Grafico.getCelula(i);
            setVertexColor(mn.x * TamanhoBloco, mn.y * TamanhoBloco, colorirVet, GraficoView);
        }
    }

    
     /**
     * Classe para representar um vertice na montagem do grafo
     */
    private class Vertex {

        int x, y, pos;
        char father;

        public Vertex(int x, int y, int curr_index, char father) {
            this.x = x;
            this.y = y;
            this.pos = curr_index;
            this.father = father;
        }
    }

     /**
     * Classe para armazenar dados de uma celula
     */
    private class Cell {

        public boolean visited, n, s, e, w;
        public int x, y, pos;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
            visited = false;
            n = s = e = w = true;
        }
    }

    public void solution(int opt) {

        switch (opt) {
            case buscaLarg:
                Grafico.buscaLarg(NoInicial, NoFinal);
                break;

            case buscaProf:
                Grafico.buscaProf(NoInicial, NoFinal);
                break;
        }

        List<Grau> solution = Grafico.getSolution();

        for (int i = 0; i < solution.size(); i++) {

            int middleStartX = (int) ((Grafico.getCelula(solution.get(i).raiz).x + (1.0 / 2.0)) * TamanhoBloco);
            int middleStartY = (int) ((Grafico.getCelula(solution.get(i).raiz).y + (1.0 / 2.0)) * TamanhoBloco);
            int middleEndX = (int) ((Grafico.getCelula(solution.get(i).distancia).x + (1.0 / 2.0)) * TamanhoBloco);
            int middleEndY = (int) ((Grafico.getCelula(solution.get(i).distancia).y + (1.0 / 2.0)) * TamanhoBloco);

            setPath(middleStartX, middleStartY, middleEndX, middleEndY, inicioCor, image);
            GraficoView = getGraficoView();
            setPath(middleStartX, middleStartY, middleEndX, middleEndY, inicioCor, GraficoView);
        }

        resolvido = true;
    }

    public void solution(int opt, int heuristic) {

        switch (opt) {
            case guloso:
                Grafico.greedy(NoInicial, NoFinal, heuristic);
                break;
            case estrela:
                Grafico.aStar(NoInicial, NoFinal, heuristic);
                break;
        }

        List<Grau> solution = Grafico.getSolution();

        for (int i = 0; i < solution.size(); i++) {

            int middleStartX = (int) ((Grafico.getCelula(solution.get(i).raiz).x + (1.0 / 2.0)) * TamanhoBloco);
            int middleStartY = (int) ((Grafico.getCelula(solution.get(i).raiz).y + (1.0 / 2.0)) * TamanhoBloco);
            int middleEndX = (int) ((Grafico.getCelula(solution.get(i).distancia).x + (1.0 / 2.0)) * TamanhoBloco);
            int middleEndY = (int) ((Grafico.getCelula(solution.get(i).distancia).y + (1.0 / 2.0)) * TamanhoBloco);

            setPath(middleStartX, middleStartY, middleEndX, middleEndY, inicioCor, image);
            GraficoView = getGraficoView();
            setPath(middleStartX, middleStartY, middleEndX, middleEndY, inicioCor, GraficoView);
        }

        resolvido = true;
    }

    public void showGraus(Main m, Painel g, int delay) {
        Thread t;
        t = new Thread() {
            @Override
            public void run() {
                clearPath();
                m.disableComponents();
                List<Grau> list = Grafico.getGraus();
                for (int i = 0; i < list.size(); i++) {

                    int middleStartX = (int) ((Grafico.getCelula(list.get(i).raiz).x + (1.0 / 2.0)) * TamanhoBloco);
                    int middleStartY = (int) ((Grafico.getCelula(list.get(i).raiz).y + (1.0 / 2.0)) * TamanhoBloco);
                    int middleEndX = (int) ((Grafico.getCelula(list.get(i).distancia).x + (1.0 / 2.0)) * TamanhoBloco);
                    int middleEndY = (int) ((Grafico.getCelula(list.get(i).distancia).y + (1.0 / 2.0)) * TamanhoBloco);

                    setPath(middleStartX, middleStartY, middleEndX, middleEndY, nivelCor, GraficoView);
                    setPath(middleStartX, middleStartY, middleEndX, middleEndY, nivelCor, image);

                    if (m.MostrarVertices.isSelected()) {
                        g.setBackground(GraficoView);
                        g.refresh();
                    } else {
                        g.setBackground(image);
                        g.refresh();
                    }

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Labirinto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                list = Grafico.getSolution();

                for (int i = 0; i < list.size(); i++) {

                    int middleStartX = (int) ((Grafico.getCelula(list.get(i).raiz).x + (1.0 / 2.0)) * TamanhoBloco);
                    int middleStartY = (int) ((Grafico.getCelula(list.get(i).raiz).y + (1.0 / 2.0)) * TamanhoBloco);
                    int middleEndX = (int) ((Grafico.getCelula(list.get(i).distancia).x + (1.0 / 2.0)) * TamanhoBloco);
                    int middleEndY = (int) ((Grafico.getCelula(list.get(i).distancia).y + (1.0 / 2.0)) * TamanhoBloco);

                    setPath(middleStartX, middleStartY, middleEndX, middleEndY, inicioCor, GraficoView);
                    setPath(middleStartX, middleStartY, middleEndX, middleEndY, inicioCor, image);

                    if (m.MostrarVertices.isSelected()) {
                        g.setBackground(GraficoView);
                        g.refresh();
                    } else {
                        g.setBackground(image);
                        g.refresh();
                    }

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Labirinto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                m.enableComponents();
                resolvido = true;
            }
        };
        t.start();
    }

    /**
     * Verifica se um vizinho e valido
     *
     * @param c Celula a ser analisada
     * @param x Coordenada x do vizinho
     * @param y Coordenada y do vizinho
     * @return True caso seja valido, False se nao.
     */
    private boolean checkOption(Cell c, int x, int y) {
        boolean valid = true;

        //se o x ou y sairem fora dos limites da matriz nao e uma opcao
        if (x < 0 || x >= cells.length
                || y < 0 || y >= cells[0].length) {
            valid = false;
        } //se ja tiver acesso a celular, nao e uma opcao
        else if (y < c.y && !c.n
                || y > c.y && !c.s
                || x < c.x && !c.w
                || x > c.x && !c.e) {
            valid = false;
        } //se a celula ja foi visitada, nao e uma opcao
        else if (cells[x][y].visited) {
            valid = false;
        }

        return valid;
    }

    /**
     * Adiciona uma parede no labirinto
     *
     * @param x coordenada x inicial do bloco
     * @param y coordenada y inicial do bloco
     * @param direction
     */
    private void addWall(int x, int y, char direction) {
        switch (direction) {
            case 'N':
                for (int i = x; i < x + TamanhoBloco; i++) {
                    image.setRGB(i, y, MuretaCor);
                }
                break;

            case 'S':
                for (int i = x; i < x + TamanhoBloco; i++) {
                    image.setRGB(i, y + TamanhoBloco, MuretaCor);
                }
                break;
            case 'W':
                for (int j = y; j < y + TamanhoBloco; j++) {
                    image.setRGB(x, j, MuretaCor);
                }
                break;
            case 'E':
                for (int j = y; j < y + TamanhoBloco; j++) {
                    image.setRGB(x + TamanhoBloco, j, MuretaCor);
                }
                break;
            default:
                System.out.println("Error: invalid direction.");
        }
    }

    /**
     * Expande uma celula ate encontrar uma proxima celula Uma nova celula e
     * encontrada quando existe uma bifurcacao excluindo a direcao de origem
     *
     * @param g Estrutura de grafos onde os vertices serao inseridos
     * @param queue Fila de analise de vertices
     * @param v Celula a ser expandida
     */
    private void expand(Grafico g, List<Vertex> queue, Vertex v) {
        int count;

        count = 0;

        //se nao existe parede para o leste
        //se pai nao estava indo a oeste
        if (!cells[v.x][v.y].e && v.father != 'W') {
            count++;
            
            //enquanto for possivel andar somente a leste e nao encontrar uma celula que ja virou um vertice
            while (!cells[v.x + count][v.y].e && cells[v.x + count][v.y].n && cells[v.x + count][v.y].s && cells[v.x + count][v.y].pos == -1){
                count++;
            }
        }
        
        //se o contador foi diferente de zero ocorreu ao menos um passo
        if (count != 0){
            
            //se a celula onde o contador ao leste parou nao for um vertice
            if (cells[v.x + count][v.y].pos == -1){
                g.addVertex(v.x + count, v.y, indiceVet);                    //cria novo vertice
                g.addAresta(new Aresta(v.pos, indiceVet, count));                //adiciona aresta entre v e o novo vertice criado
                g.addAresta(new Aresta(indiceVet, v.pos, count));
                cells[v.x + count][v.y].pos = indiceVet;                     //define a posicao da celula para a posicao do novo vertice
                queue.add(new Vertex(v.x + count, v.y, indiceVet, 'E'));     //adiciona o vertice para ser analisado
                
                //se o vertice criado for o vertice final, define o vertice final
                if (v.x + count == (largura / TamanhoBloco - 1) && v.y == (altura / TamanhoBloco - 1)){
                    NoFinal = indiceVet;
                }
                
                indiceVet++;
            }
            //se a celula onde o contador parou e um vertice
            //apenas adiciona uma aresta
            else{
                g.addAresta(new Aresta(v.pos, cells[v.x + count][v.y].pos, count));
//                g.addAresta(new Aresta(cells[v.x + count][v.y].pos, v.pos, count));
            }
        }

        count = 0;

        //se nao existe parede para o S
        //se pai nao estava indo a Norte
        if (!cells[v.x][v.y].s && v.father != 'N') {
            count++;
            
            //enquanto for possivel andar somente a leste e nao encontrar uma celula que ja virou um vertice
            while (!cells[v.x][v.y + count].s && cells[v.x][v.y + count].e && cells[v.x][v.y + count].w && cells[v.x][v.y + count].pos == -1){
                count++;
            }
        }
        
        //se o contador foi diferente de zero ocorreu ao menos um passo
        if (count != 0){
            
            //se a celula onde o contador ao sul parou nao for um vertice
            if (cells[v.x][v.y + count].pos == -1){
                g.addVertex(v.x, v.y + count, indiceVet);                    //cria novo vertice
                g.addAresta(new Aresta(v.pos, indiceVet, count));                //adiciona aresta entre v e o novo vertice criado
                g.addAresta(new Aresta(indiceVet, v.pos, count));
                cells[v.x][v.y + count].pos = indiceVet;                     //define a posicao da celula para a posicao do novo vertice
                queue.add(new Vertex(v.x, v.y + count, indiceVet, 'S'));     //adiciona o vertice para ser analisado
                
                //se o vertice criado for o vertice final, define o vertice final
                if (v.x == (largura / TamanhoBloco - 1) && v.y + count == (altura / TamanhoBloco - 1)){
                    NoFinal = indiceVet;
                }
                
                indiceVet++;
            }
            //se a celula onde o contador parou e um vertice
            //apenas adiciona uma aresta
            else{
                g.addAresta(new Aresta(v.pos, cells[v.x][v.y + count].pos, count));
//                g.addAresta(new Aresta(cells[v.x][v.y + count].pos, v.pos, count));
            }
        }
       
        count = 0;

        //se nao existe parede para o W
        //se pai nao estava indo a leste
        if (!cells[v.x][v.y].w && v.father != 'L') {
            count++;
            
            //enquanto for possivel andar somente a oeste e nao encontrar uma celula que ja virou um vertice
            while (!cells[v.x - count][v.y].w && cells[v.x - count][v.y].n && cells[v.x - count][v.y].s && cells[v.x - count][v.y].pos == -1){
                count++;
            }
        }
        
        //se o contador foi diferente de zero ocorreu ao menos um passo
        if (count != 0){
            
            //se a celula onde o contador ao oeste parou nao for um vertice
            if (cells[v.x - count][v.y].pos == -1){
                g.addVertex(v.x - count, v.y, indiceVet);                    //cria novo vertice
                g.addAresta(new Aresta(v.pos, indiceVet, count));                //adiciona aresta entre v e o novo vertice criado
                g.addAresta(new Aresta(indiceVet, v.pos, count));
                cells[v.x - count][v.y].pos = indiceVet;                     //define a posicao da celula para a posicao do novo vertice
                queue.add(new Vertex(v.x - count, v.y, indiceVet, 'W'));     //adiciona o vertice para ser analisado
                
                //se o vertice criado for o vertice final, define o vertice final
                if (v.x - count == (largura / TamanhoBloco - 1) && v.y == (altura / TamanhoBloco - 1)){
                    NoFinal = indiceVet;
                }
                
                indiceVet++;
            }
            //se a celula onde o contador parou e um vertice
            //apenas adiciona uma aresta 
            else{
                g.addAresta(new Aresta(v.pos, cells[v.x - count][v.y].pos, count));
//                g.addAresta(new Aresta(cells[v.x - count][v.y].pos, v.pos, count));
            }
        }
        
        count = 0;

        //se nao existe parede para o S
        //se pai nao estava indo a Norte
        if (!cells[v.x][v.y].n && v.father != 'S') {
            count++;
            
            //enquanto for possivel andar somente a leste e nao encontrar uma celula que ja virou um vertice
            while (!cells[v.x][v.y - count].n && cells[v.x][v.y - count].e && cells[v.x][v.y - count].w && cells[v.x][v.y - count].pos == -1){
                count++;
            }
        }
        
        //se o contador foi diferente de zero ocorreu ao menos um passo
        if (count != 0){
            
            //se a celula onde o contador ao leste parou nao for um vertice
            if (cells[v.x][v.y - count].pos == -1){
                g.addVertex(v.x, v.y - count, indiceVet);                    //cria novo vertice
                g.addAresta(new Aresta(v.pos, indiceVet, count));                //adiciona aresta entre v e o novo vertice criado
                g.addAresta(new Aresta(indiceVet, v.pos, count));
                cells[v.x][v.y - count].pos = indiceVet;                     //define a posicao da celula para a posicao do novo vertice
                queue.add(new Vertex(v.x, v.y - count, indiceVet, 'N'));     //adiciona o vertice para ser analisado
                
                //se o vertice criado for o vertice final, define o vertice final
                if (v.x == (largura / TamanhoBloco - 1) && v.y - count == (altura / TamanhoBloco - 1)){
                    NoFinal = indiceVet;
                }
                
                indiceVet++;
            }
            //se a celula onde o contador parou e um vertice
            //apenas adiciona uma aresta
            else{
                g.addAresta(new Aresta(v.pos, cells[v.x][v.y - count].pos, count));
//                g.addAresta(new Aresta(cells[v.x][v.y - count].pos, v.pos, count));
            }
        }
    }

    /**
     * Gera a imagem do labirinto
     */
    private void generateImage() {

        //define a imagem como branco
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                image.setRGB(i, j, fundoCor);
            }
        }

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].n) {
                    addWall(i * TamanhoBloco, j * TamanhoBloco, 'N');
                }

                if (cells[i][j].s) {
                    addWall(i * TamanhoBloco, j * TamanhoBloco, 'S');
                }

                if (cells[i][j].w) {
                    addWall(i * TamanhoBloco, j * TamanhoBloco, 'W');
                }

                if (cells[i][j].e) {
                    addWall(i * TamanhoBloco, j * TamanhoBloco, 'E');
                }
            }
        }

        setBlockColor(0, 0, inicioCor, image);
        setBlockColor((largura / TamanhoBloco - 1) * TamanhoBloco, (altura / TamanhoBloco - 1) * TamanhoBloco, fimCor, image);
    }

    /**
     * Visita todos os visinhos possiveis de forma aleatoria, retirando as
     * paredes de forma que sempre há ao menos um caminho entre quaquer ponto no
     * grafo
     *
     * @param c Celula pai
     * @param direction Direcao de onde veio
     */
    private void generateMaze(Cell c, char direction) {
//        System.out.printf("> Visitando celula [%d][%d]\n", c.x, c.y);
        c.visited = true;

        switch (direction) {
            case 'N':
                c.n = false;
                break;

            case 'S':
                c.s = false;
                break;

            case 'W':
                c.w = false;
                break;

            case 'E':
                c.e = false;
                break;
        }

        List<Cell> options = getOptions(c);

//        System.out.printf("\tOpcoes obtidas: %d\n", options.size());
        while (!options.isEmpty()) {
            int index = RDM.nextInt(options.size());
            Cell nextCell = options.remove(index);

            if (c.y > nextCell.y) {
                c.n = false;
                generateMaze(nextCell, 'S');
            } else if (c.x < nextCell.x) {
                c.e = false;
                generateMaze(nextCell, 'W');
            } else if (c.y < nextCell.y) {
                c.s = false;
                generateMaze(nextCell, 'N');
            } else if (c.x > nextCell.x) {
                c.w = false;
                generateMaze(nextCell, 'E');
            }

            options = getOptions(c);
        }
    }

    private void randomizeMaze() {
        int numIte = (int) (cells.length * cells[0].length * this.proporcao);
        Cell c;
        char[] walls;

        for (int i = 0; i < numIte; i++) {
            int x = RDM.nextInt(cells.length);
            int y = RDM.nextInt(cells[0].length);
            c = cells[x][y];
            //nao retira paredes de celulas da borda.
            if (x > 0 && x < cells.length - 1
                    && y > 0 && y < cells[0].length - 1) {

                walls = getWalls(c);
                removeWall(c, walls[RDM.nextInt(2)]);
            }
        }
    }

    private void removeWall(Cell c, char wall) {
        switch (wall) {
            case 'E':
                c.e = false;
                cells[c.x + 1][c.y].w = false;
                break;
            case 'S':
                c.s = false;
                cells[c.x][c.y + 1].n = false;
                break;
            case 'W':
                c.w = false;
                cells[c.x - 1][c.y].e = false;
                break;
            case 'N':
                c.n = false;
                cells[c.x][c.y - 1].s = false;
                break;
        }
    }

    private Grafico generateGrafico() {
        Grafico g = new Grafico();
        List<Vertex> queue = new ArrayList<>();

        //marca todas as celulas como nao visitada
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells[0].length; j++) {
                cell[j].visited = false;
                cell[j].pos = -1;
            }
        }

        queue.add(new Vertex(0, 0, indiceVet, ' '));
        cells[0][0].pos = indiceVet;
        NoInicial = indiceVet;
        g.addVertex(0, 0, indiceVet);
        indiceVet++;
        
        while (!queue.isEmpty()) {
            Vertex v = queue.remove(0);
            expand(g, queue, v);
        }

        return g;
    }

    /**
     * Define a cor de um bloco
     *
     * @param x Ponto inicial do bloco
     * @param y Ponto final do bloco
     * @param color Cor do bloco
     * @param image Imagem a ser pintada
     */
    public void setBlockColor(int x, int y, int color, BufferedImage image) {
        for (int i = x; i < largura && i <= x + TamanhoBloco; i++) {
            for (int j = y; j < this.altura && j <= y + TamanhoBloco; j++) {
                if (i % TamanhoBloco != 0 && j % TamanhoBloco != 0) {
                    image.setRGB(i, j, color);
                }
            }
        }
    }

    /**
     * Pinta um vertice em proporcao ao tamanho do bloco
     *
     * @param x coordenada x do inicio do bloco
     * @param y coordenada y do inicio do bloco
     * @param color Cor do vertice
     * @param image Imagem a ser pintada
     */
    public void setVertexColor(int x, int y, int color, BufferedImage image) {

        int iterations = (TamanhoBloco) - (int) (ProporcaoVet * TamanhoBloco);
        int limit = iterations + (iterations / 2);
        int start = (TamanhoBloco / 2) - (iterations / 4);

        for (int i = x + start; i < x + limit; i++) {
            for (int j = y + start; j < y + limit; j++) {
                image.setRGB(i, j, color);
            }
        }
    }

    /**
     * Pinta o caminho de solucao dado um bloco de inicio e um bloco final
     *
     * @param x1 Coordenada x do bloco inicial
     * @param y1 Coordenada y do bloco inicial
     * @param x2 Coordenanda x do ultimo bloco
     * @param y2 Coordenada y do ultimo bloco
     * @param color Cor a ser pintado
     * @param image Buffered image da ser tingida
     * @return
     */
    public BufferedImage setPath(int x1, int y1, int x2, int y2, int color, BufferedImage image) {
        Graphics2D g2d = image.createGraphics();
        g2d.setStroke(new BasicStroke((float) 0.15 * TamanhoBloco));
        g2d.setColor(new Color(color));
        g2d.drawLine(x1, y1, x2, y2);
        return image;
    }

    //pegar o array de paredes validas
    private char[] getWalls(Cell c) {
        char[] walls = new char[3];
        int i = 0;
        walls[0] = ' ';
        walls[1] = ' ';

        if (c.e) {
            walls[i] = 'E';
            i++;
        }

        if (c.s) {
            walls[i] = 'S';
            i++;
        }

        if (c.w) {
            walls[i] = 'W';
            i++;
        }

        if (c.n) {
            walls[i] = 'N';
            i++;
        }

        return walls;
    }

    /**
     * Verifica se o labirinto foi ou nao resolvido
     *
     * @return True se tiver sido resolvido, falso senao
     */
    public boolean isresolvido() {
        return resolvido;
    }

    public int getDistance() {
        return Grafico.getDistance();
    }

    public long getExecutionTime() {
        return Grafico.executionTime;
    }

    /**
     * Obtem a quantidade de aresta do grafo que representa o labirinto
     *
     * @return quantidade de arestas
     */
    public int getNumArestas() {
        return Grafico.getNumArestas();
    }

    /**
     * Obtem o numero de vertices do grafo que representa o labirinto
     *
     * @return obtem o numero de vertices
     */
    public int getNumVertices() {
        return Grafico.getNumVertices();
    }

    /**
     * Retorna a imagem do labirinto com a visualizacao grafica
     *
     * @return imagem com os vertices pintados
     */
    public final BufferedImage getGraficoView() {
        if (GraficoView == null) {
            //copia a imagem do labirinto
            GraficoView = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < largura; i++) {
                for (int j = 0; j < altura; j++) {
                    GraficoView.setRGB(i, j, image.getRGB(i, j));
                }
            }

            //pinta os vertices
            int numVertices = Grafico.getNumVertices();
            for (int i = 0; i < numVertices; i++) {
                Celula mn = Grafico.getCelula(i);
                setVertexColor(mn.x * TamanhoBloco, mn.y * TamanhoBloco, colorirVet, GraficoView);
            }
        }

        return GraficoView;
    }

    /**
     * Obtem a imagem do labirinto apos ter sido criado
     *
     * @return imagem do labirinto
     */
    public BufferedImage getMazeImage() {
        return this.image;
    }

    /**
     * Obtem o grafo do labirinto
     *
     * @return grafo gerado
     */
    public final Grafico getGrafico() {
        return this.Grafico;
    }

    /**
     * Pega a lista de vizinhos validos para ser visitado
     *
     * @param c Celula a ser analizada
     * @return Lista de vizinhos validos
     */
    private List<Cell> getOptions(Cell c) {
        List<Cell> result = new ArrayList<>();

        //verifica se a celula acima e uma opcao
        if (checkOption(c, c.x, c.y - 1)) {
            result.add(cells[c.x][c.y - 1]);
        }

        //verifica se a celula abaixo e uma opcao
        if (checkOption(c, c.x, c.y + 1)) {
            result.add(cells[c.x][c.y + 1]);
        }

        //verifica se a celula a esquerda e uma opcao
        if (checkOption(c, c.x - 1, c.y)) {
            result.add(cells[c.x - 1][c.y]);
        }

        //verifica se a celula a direita e uma opcao
        if (checkOption(c, c.x + 1, c.y)) {
            result.add(cells[c.x + 1][c.y]);
        }

        return result;
    }
}
