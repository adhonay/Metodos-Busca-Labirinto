package Modelo;

import Modelo.Aresta;
import java.util.ArrayList;
import java.util.List;

public class Celula{
    
    public int x, y, cor, pai,  pos;
    public double heuristica, distancia;
    
    public List<Aresta> adjacente;
    public Celula(int x, int y, int pos){
        
        this.adjacente = new ArrayList<>();
        this.x = x;
        
        this.y = y;
        this.pos = pos;
        
    }
}
