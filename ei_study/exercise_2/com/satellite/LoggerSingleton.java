package com.satellite;

import java.io.IOException;
import java.util.logging.*;

public class LoggerSingleton {
    private static Logger logger = null;

    private LoggerSingleton() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            synchronized (LoggerSingleton.class) {
                if (logger == null) {
                    logger = Logger.getLogger(LoggerSingleton.class.getName());
                    try {
                        Handler fileHandler = new FileHandler("logs/satellite.log", true);
                        fileHandler.setFormatter(new SimpleFormatter());
                        logger.addHandler(fileHandler);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "Failed to initialize logger handler.", e);
                    }
                    logger.setLevel(Level.ALL);
                }
            }
        }
        return logger;
    }
}
