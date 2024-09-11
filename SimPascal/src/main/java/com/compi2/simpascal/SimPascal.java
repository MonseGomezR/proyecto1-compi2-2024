package com.compi2.simpascal;

import com.compi2.simpascal.interfaz.MFrame;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

/**
 *
 * @author mgome
 */
public class SimPascal {

    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("raven.table");
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            new MFrame().setVisible(true);
        });
    }

}
