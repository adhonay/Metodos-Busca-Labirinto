package Tela;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Classe que representa um panel que contem o labirinto.
 * @author Isync
 */
public class Painel extends javax.swing.JPanel {
    
    public static final int BACKGROUND_COLOR = Color.white.getRGB();
    public static final int GRID_COLOR = Color.black.getRGB();
    public static final int BLOCK_COLOR = Color.white.getRGB();
    
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    
    private BufferedImage fundo;
    
    public Painel() {
        initComponents();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fundo, 0, 0, null);
    }
    
    /**
     * Faz atualizacoes do labirinto.
     */
    public void refresh(){
        repaint();
    }
    
    /**
     * Mudar a cor do fundo.
     */
    public void setBackground(BufferedImage fundo){
        this.fundo = fundo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
