package Modelo;

/**
 * Classe que contem atributos para modelagem de arestas ponderadas para o grafo. 
 * @author Isync
 */
public class Aresta {
    public int peso;
    public int raiz, distancia;
    
    public Aresta(){}
    
    public Aresta(int raiz, int distancia, int peso){
        this.raiz = raiz;
        this.distancia = distancia;
        this.peso = peso;
    }
}
