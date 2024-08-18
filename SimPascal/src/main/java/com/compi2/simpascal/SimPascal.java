package com.compi2.simpascal;

import com.compi2.simpascal.interfaz.MFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author mgome
 */
public class SimPascal {

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // handle exception
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MFrame().setVisible(true);
        });
    }

}
