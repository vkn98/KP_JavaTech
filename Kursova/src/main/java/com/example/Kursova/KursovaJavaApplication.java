package com.example.Kursova;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Kursova.swing.AppLauncher;

@SpringBootApplication
public class KursovaJavaApplication {

    public static void main(String[] args) {
        AppLauncher.createAndShowGUI();
    }
}

