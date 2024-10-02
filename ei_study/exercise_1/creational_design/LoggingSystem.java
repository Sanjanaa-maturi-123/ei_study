// *** CREATIONAL DESIGN PATTERN USE CASE 1: Singleton Pattern ***
// *** Use Case: Logging System for an Application ***
//
// This implementation demonstrates the **Singleton Pattern**, where only one instance of the logging system is created and shared globally.
//
// 1. The **ApplicationLogger** class ensures that there is only a single instance (singleton) throughout the application.
// 2. The **getInstance** method provides a global point of access and uses synchronization to ensure thread safety.
// 3. **Defensive programming** ensures that only valid, non-empty messages are logged.
// 4. **Logging** is used to monitor when a new instance is created and when messages are logged.
//
// The Singleton pattern is ideal for logging systems where only one instance should exist for the application lifecycle.

import java.util.logging.Level;
import java.util.logging.Logger;

class ApplicationLogger {
    private static ApplicationLogger instance;
    private static final Logger logger = Logger.getLogger(ApplicationLogger.class.getName());

    private ApplicationLogger() {}

    public static ApplicationLogger getInstance() {
        if (instance == null) {
            synchronized (ApplicationLogger.class) {
                if (instance == null) {
                    instance = new ApplicationLogger();
                    logger.info("New Logger instance created.");
                }
            }
        }
        return instance;
    }

    public void logMessage(String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        logger.log(Level.INFO, message);
    }
}

public class LoggingSystem {
    public static void main(String[] args) {
        ApplicationLogger logger = ApplicationLogger.getInstance();
        logger.logMessage("System started.");

        ApplicationLogger anotherLogger = ApplicationLogger.getInstance();
        anotherLogger.logMessage("Performing some tasks.");
    }
}
