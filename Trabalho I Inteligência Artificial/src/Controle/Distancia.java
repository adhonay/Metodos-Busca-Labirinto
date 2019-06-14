package Controle;

/* Classe que contem Heuristicas usadas nas buscas. 
 * Distancia entre dois pontos.
 */
public class Distancia {
    
    public static final int Distancia_Euclid = 1;
    
    public static final int Distancia_Manhat = 2;
     
    public static double DistanciaManhat(int x1, int y1, int x2, int y2){
        return Math.abs(x2-x1) + Math.abs(y2 - y1);
    }
    
    public static double DistanciaEuclid(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
