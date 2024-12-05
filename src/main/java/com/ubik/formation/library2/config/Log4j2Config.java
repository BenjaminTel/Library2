package com.ubik.formation.library2.config;

import org.springframework.context.annotation.Configuration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Configuration
public class Log4j2Config {

    public void configureLogging() {
        // Créer un layout de pattern
        Layout layout = PatternLayout.newBuilder()
                .withPattern("%d{yyyy-MM-dd HH:mm:ss} - %msg%n")
                .build();

        // Créer un appender console
        ConsoleAppender consoleAppender = ConsoleAppender.newBuilder()
                .setName("Console")
                .setLayout(layout)
                .build();
        consoleAppender.start();

        // Créer un appender fichier
        FileAppender fileAppender = FileAppender.newBuilder()
                .withFileName("logs/app.log")
                .setName("File")
                .setLayout(layout)
                .withAppend(true)
                .build();
        fileAppender.start();

        // Configurer le LoggerContext
        LoggerContext context = (LoggerContext) LogManager.getContext(false);

        // Configurer le logger racine
        context.getConfiguration().getRootLogger().setLevel(Level.WARN);
        context.getConfiguration().getRootLogger().addAppender(consoleAppender, Level.WARN, null);
        context.getConfiguration().getRootLogger().addAppender(fileAppender, Level.WARN, null);

        // Configurer les loggers spécifiques
        context.getLogger("org.hibernate.SQL").setLevel(Level.DEBUG); // Pour les requêtes SQL
        context.getLogger("org.springframework").setLevel(Level.WARN); // Seulement les warnings et erreurs
        context.getLogger("com.ubik.formation").setLevel(Level.WARN); // Seulement les warnings et erreurs

        // Mettre à jour les loggers
        context.updateLoggers();
    }
}
