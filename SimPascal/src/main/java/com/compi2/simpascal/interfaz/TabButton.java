package com.compi2.simpascal.interfaz;

//import com.compi1.javacraft.archivos.ArchivoManager;
import static com.compi2.simpascal.interfaz.TabPanel.estadoHover;
import static com.compi2.simpascal.interfaz.TabPanel.estadoNormal;
import static com.compi2.simpascal.interfaz.TabPanel.estadoClick;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author mgome
 */
public class TabButton extends JPanel {

    private JButton mainButton;
    private JButton closeButton;
    private final Color transparente = new Color(0, 0, 0, 0);
    public boolean colorSeleccionado = false;

    public TabButton(String buttonText) {
        setLayout(new BorderLayout());
        setBackground(estadoNormal);
        int ancho = buttonText.length() * 13 + 15;
        System.out.println(ancho);
        setPreferredSize(new Dimension(ancho, 30));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        mainButton = new JButton(buttonText);
        mainButton.setFont(new java.awt.Font("Inter", 1, 14));
        mainButton.setPreferredSize(new Dimension(100, 30));
        mainButton.setBackground(transparente);
        mainButton.setForeground(Color.white);
        mainButton.setBorder(null);
        mainButton.setBorderPainted(false);
        mainButton.setFocusPainted(false);

        closeButton = new JButton("x");
        closeButton.setFont(new java.awt.Font("Inter", 1, 10));
        closeButton.setPreferredSize(new Dimension(30, 30));
        closeButton.setBackground(transparente);
        closeButton.setForeground(Color.white);
        closeButton.setBorder(null);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);

        mainButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!colorSeleccionado) {
                    setBackground(estadoHover);
                }
                updateUI();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!colorSeleccionado) {
                    setBackground(estadoNormal);
                }
                updateUI();
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (!colorSeleccionado) {
                    setBackground(estadoClick);
                }
                updateUI();
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (!colorSeleccionado) {
                    setBackground(estadoNormal);
                }
                updateUI();
            }
        });
        
        
        add(mainButton, BorderLayout.CENTER);

        closeButton.addActionListener((ActionEvent e) -> {
            Container parent1 = TabButton.this.getParent();
            if (parent1 != null) {
                parent1.remove(TabButton.this);
                parent1.revalidate();
                parent1.repaint();
            }
        });

        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                
                closeButton.setBackground(Color.red);
                updateUI();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(transparente);
                updateUI();
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(new Color(150, 15, 10));
                updateUI();
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(transparente);
                updateUI();
            }
        });

        add(closeButton, BorderLayout.EAST);
    }

    public JButton getMainButton() {
        return mainButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
    
    public void setButtonText(String text) {
        mainButton.setText(text);
    }
}
