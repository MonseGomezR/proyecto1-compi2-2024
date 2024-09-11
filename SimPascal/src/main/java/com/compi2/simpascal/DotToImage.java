package com.compi2.simpascal;

import java.io.*;
import java.awt.Desktop;
import java.nio.file.*;

/**
 *
 * @author mgome
 */
public class DotToImage {

    public void generar(String name, String dotCode) {
        String dotFilePath = name + ".dot";
        String outputImagePath = name + ".png";


        try {
            Files.write(Paths.get(dotFilePath), dotCode.getBytes());

            String[] command = {"dot", "-Tpng", dotFilePath, "-o", outputImagePath};

            Process process = new ProcessBuilder(command).start();
            process.waitFor();

            File imageFile = new File(outputImagePath);
            if (imageFile.exists()) {
                Desktop.getDesktop().open(imageFile);
            } else {
                System.err.println("La imagen no fue generada.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}