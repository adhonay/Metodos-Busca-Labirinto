package Tela;

import Controle.Labirinto;
import Controle.Distancia;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Isync
 */
public class Main extends javax.swing.JFrame {

    public static final int DEFAULT_TamanhoBloco = 20; 
    private Labirinto maze;

    public Main() {

        initComponents();

        maze = new Labirinto(LabirintoTela.getWidth(), LabirintoTela.getHeight(), DEFAULT_TamanhoBloco, Labirinto.parametroProp);
        LabirintoTela.setBackground(maze.getMazeImage());
        LabirintoTela.refresh();
        MostrarVertices.setSelected(false);
        ConfiguracaoPainel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trabalho de Inteligência Artificial", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ConfiguracaoPainel = new javax.swing.JPanel();
        methodsPanel = new javax.swing.JPanel();
        methodLabel = new javax.swing.JLabel();
        ResolverTela = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        optionsPanel = new javax.swing.JPanel();
        optionsLabel = new javax.swing.JLabel();
        MostrarVertices = new javax.swing.JCheckBox();
        PassoPassoTela = new javax.swing.JButton();
        GerarNovoLabirinto = new javax.swing.JButton();
        informationPanel = new javax.swing.JPanel();
        informationLabel = new javax.swing.JLabel();
        spentTimeTextField = new javax.swing.JTextField();
        DistanciaTela = new javax.swing.JLabel();
        TempoGastoTela = new javax.swing.JLabel();
        distanceTextField = new javax.swing.JTextField();
        LabirintoTela = new Tela.Painel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Metodos de Busca");
        setBackground(new java.awt.Color(51, 0, 0));
        setResizable(false);

        ConfiguracaoPainel.setBackground(new java.awt.Color(51, 0, 0));
        ConfiguracaoPainel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        methodsPanel.setBackground(new java.awt.Color(0, 0, 0));
        methodsPanel.setForeground(new java.awt.Color(255, 0, 51));
        methodsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        methodLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        methodLabel.setForeground(new java.awt.Color(153, 0, 0));
        methodLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        methodLabel.setText("MÉTODOS");
        methodsPanel.add(methodLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 68, -1));

        ResolverTela.setBackground(new java.awt.Color(0, 0, 0));
        ResolverTela.setText("Resolver");
        ResolverTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResolverTelaActionPerformed(evt);
            }
        });
        methodsPanel.add(ResolverTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 98, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Largura", "Gulosa", "Profundidade", "A*" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        methodsPanel.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        optionsPanel.setBackground(new java.awt.Color(0, 0, 0));

        optionsLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        optionsLabel.setForeground(new java.awt.Color(153, 0, 0));
        optionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        optionsLabel.setText("OPÇÕES");

        MostrarVertices.setBackground(new java.awt.Color(0, 0, 0));
        MostrarVertices.setForeground(new java.awt.Color(255, 255, 255));
        MostrarVertices.setText("Mostrar vertices");
        MostrarVertices.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                MostrarVerticesStateChanged(evt);
            }
        });

        PassoPassoTela.setBackground(new java.awt.Color(0, 0, 0));
        PassoPassoTela.setText("Passo");
        PassoPassoTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassoPassoTelaActionPerformed(evt);
            }
        });

        GerarNovoLabirinto.setBackground(new java.awt.Color(0, 0, 0));
        GerarNovoLabirinto.setText("Gerar novo labirinto");
        GerarNovoLabirinto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GerarNovoLabirintoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addComponent(MostrarVertices)
                .addGap(18, 18, 18)
                .addComponent(PassoPassoTela)
                .addGap(27, 27, 27))
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(optionsPanelLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(optionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(optionsPanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(GerarNovoLabirinto)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addComponent(optionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MostrarVertices, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PassoPassoTela))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GerarNovoLabirinto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        informationPanel.setBackground(new java.awt.Color(0, 0, 0));

        informationLabel.setBackground(new java.awt.Color(255, 255, 255));
        informationLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        informationLabel.setForeground(new java.awt.Color(153, 0, 0));
        informationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        informationLabel.setText("INFORMAÇÕES");

        spentTimeTextField.setEditable(false);
        spentTimeTextField.setBackground(new java.awt.Color(255, 255, 255));

        DistanciaTela.setForeground(new java.awt.Color(255, 255, 255));
        DistanciaTela.setText("Distância");

        TempoGastoTela.setForeground(new java.awt.Color(255, 255, 255));
        TempoGastoTela.setText("Tempo gasto");

        distanceTextField.setEditable(false);
        distanceTextField.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        informationPanelLayout.setHorizontalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spentTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TempoGastoTela))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informationPanelLayout.createSequentialGroup()
                        .addComponent(distanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informationPanelLayout.createSequentialGroup()
                        .addComponent(DistanciaTela)
                        .addGap(51, 51, 51))))
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(informationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        informationPanelLayout.setVerticalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addComponent(informationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TempoGastoTela)
                    .addComponent(DistanciaTela))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(distanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spentTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LabirintoTelaLayout = new javax.swing.GroupLayout(LabirintoTela);
        LabirintoTela.setLayout(LabirintoTelaLayout);
        LabirintoTelaLayout.setHorizontalGroup(
            LabirintoTelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1095, Short.MAX_VALUE)
        );
        LabirintoTelaLayout.setVerticalGroup(
            LabirintoTelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ConfiguracaoPainelLayout = new javax.swing.GroupLayout(ConfiguracaoPainel);
        ConfiguracaoPainel.setLayout(ConfiguracaoPainelLayout);
        ConfiguracaoPainelLayout.setHorizontalGroup(
            ConfiguracaoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfiguracaoPainelLayout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addGroup(ConfiguracaoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfiguracaoPainelLayout.createSequentialGroup()
                        .addComponent(LabirintoTela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfiguracaoPainelLayout.createSequentialGroup()
                        .addComponent(methodsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(informationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(274, 274, 274))))
        );
        ConfiguracaoPainelLayout.setVerticalGroup(
            ConfiguracaoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfiguracaoPainelLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(LabirintoTela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ConfiguracaoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConfiguracaoPainelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConfiguracaoPainelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(methodsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConfiguracaoPainelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(informationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ConfiguracaoPainel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ConfiguracaoPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void enableComponents() {
        jComboBox1.setEnabled(true);
        GerarNovoLabirinto.setEnabled(true);
        ResolverTela.setEnabled(true);
        PassoPassoTela.setEnabled(true);
    }

    public void disableComponents() {
        jComboBox1.setEnabled(false);
        GerarNovoLabirinto.setEnabled(false);
        ResolverTela.setEnabled(false);
        PassoPassoTela.setEnabled(false);
    }

    private void GerarNovoLabirintoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GerarNovoLabirintoActionPerformed
       //tamanho do labirinto
        int tamanho = 20; 
        double proporcao = 0.1;

        if (tamanho < 6 || tamanho > DEFAULT_TamanhoBloco) {
            JOptionPane.showMessageDialog(this, "ERRO. O tamanho do bloco deve estar entre 6 e 40.",
                    "Limite excedido", JOptionPane.ERROR_MESSAGE);
        } else if (proporcao < 0 || proporcao > 0.9) {
            JOptionPane.showMessageDialog(this, "ERRO. A proporcao deve estar entre 0 e 0.9.",
                    "Limite excedido", JOptionPane.ERROR_MESSAGE);
        } else {
            maze = new Labirinto(LabirintoTela.getWidth(), LabirintoTela.getHeight(), tamanho, proporcao);
            distanceTextField.setText("");
            if (MostrarVertices.isSelected()) {
                LabirintoTela.setBackground(maze.getGraficoView());
                LabirintoTela.refresh();
            } else {
                LabirintoTela.setBackground(maze.getMazeImage());
                LabirintoTela.refresh();
            }
        }
    }//GEN-LAST:event_GerarNovoLabirintoActionPerformed

    private void MostrarVerticesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_MostrarVerticesStateChanged
        if (MostrarVertices.isSelected()) {
            LabirintoTela.setBackground(maze.getGraficoView());
            LabirintoTela.refresh();
        } else {
            LabirintoTela.setBackground(maze.getMazeImage());
            LabirintoTela.refresh();
        }
    }//GEN-LAST:event_MostrarVerticesStateChanged

    private void ResolverTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResolverTelaActionPerformed

        if (jComboBox1.getSelectedItem().toString() == "Largura") {
            maze.solution(Labirinto.buscaLarg);
        } else if (jComboBox1.getSelectedItem().toString() == "Profundidade") {
            maze.solution(Labirinto.buscaProf);
        } else if (jComboBox1.getSelectedItem().toString() == "Gulosa") {
            maze.solution(Labirinto.guloso, Distancia.Distancia_Manhat);
        } else {
            maze.solution(Labirinto.estrela, Distancia.Distancia_Euclid);
        }

        distanceTextField.setText("" + maze.getDistance());
        spentTimeTextField.setText(maze.getExecutionTime() + " ms");

        if (MostrarVertices.isSelected()) {
            LabirintoTela.setBackground(maze.getGraficoView());
            LabirintoTela.refresh();
        } else {
            LabirintoTela.setBackground(maze.getMazeImage());
            LabirintoTela.refresh();
        }

    }//GEN-LAST:event_ResolverTelaActionPerformed

    private void PassoPassoTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassoPassoTelaActionPerformed
        try {
            int delay = 20;// setar delay
            if (jComboBox1.getSelectedItem().toString() == "Largura") {
                maze.solution(Labirinto.buscaLarg);
            } else if (jComboBox1.getSelectedItem().toString() == "Profundidade") {
                maze.solution(Labirinto.buscaProf);
            } else if (jComboBox1.getSelectedItem().toString() == "Gulosa") {
                maze.solution(Labirinto.guloso, Distancia.Distancia_Euclid);

            } else {
                maze.solution(Labirinto.estrela, Distancia.Distancia_Euclid);
            }
            distanceTextField.setText("" + maze.getDistance());
            spentTimeTextField.setText(maze.getExecutionTime() + " ms");
            maze.showGraus(this, LabirintoTela, delay);
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(this, "ERRO. Atraso nao possivel.",
                    "ERRO. Conversao para inteiro nao possivel", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_PassoPassoTelaActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (maze.isresolvido()) {
            maze.clearPath();
            if (MostrarVertices.isSelected()) {
                LabirintoTela.setBackground(maze.getGraficoView());
            } else {
                LabirintoTela.setBackground(maze.getMazeImage());
            }
            LabirintoTela.refresh();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ConfiguracaoPainel;
    private javax.swing.JLabel DistanciaTela;
    private javax.swing.JButton GerarNovoLabirinto;
    private Tela.Painel LabirintoTela;
    public javax.swing.JCheckBox MostrarVertices;
    private javax.swing.JButton PassoPassoTela;
    private javax.swing.JButton ResolverTela;
    private javax.swing.JLabel TempoGastoTela;
    private javax.swing.JTextField distanceTextField;
    private javax.swing.JLabel informationLabel;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel methodLabel;
    private javax.swing.JPanel methodsPanel;
    private javax.swing.JLabel optionsLabel;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JTextField spentTimeTextField;
    // End of variables declaration//GEN-END:variables
}
