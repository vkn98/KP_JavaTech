package com.example.Kursova.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.example.Kursova.KursovaJavaApplication;
import java.io.OutputStream;
import java.io.PrintStream;

public class AppLauncher {

    private static boolean isRunning = false;
    private static SpringApplication app;
    private static ConfigurableApplicationContext context;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Spring Boot Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JTextArea logArea = new JTextArea(15, 40);
        logArea.setEditable(false);  
        JScrollPane scrollPane = new JScrollPane(logArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                logArea.append(String.valueOf((char) b));
                logArea.setCaretPosition(logArea.getDocument().getLength()); 
            }
        });
        System.setOut(printStream);
        System.setErr(printStream);

        JPanel panel = new JPanel();
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    isRunning = true;
                    app = new SpringApplication(KursovaJavaApplication.class);
                    context = app.run();
                    logArea.append("Spring Boot application started.\n");
                } else {
                    logArea.append("Application is already running.\n");
                }
            }
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    isRunning = false;
                    if (context != null) {
                        context.close();
                        logArea.append("Spring Boot application stopped.\n");
                    }
                } else {
                    logArea.append("Application is not running.\n");
                }
            }
        });

        panel.add(startButton);
        panel.add(stopButton);

        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}



