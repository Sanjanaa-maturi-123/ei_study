package com.satellite;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = LoggerSingleton.getInstance();
    private static Satellite satellite = new Satellite();
    private static CommandInvoker invoker = new CommandInvoker();

    public static void main(String[] args) {
        logger.log(Level.INFO, "Satellite Command System Started.");

        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram) {
            try {
                showMenu();
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    exitProgram = true;
                    continue;
                }

                String[] commandParts = input.split("\s+");
                String commandName = commandParts[0].toLowerCase();

                switch (commandName) {
                    case "rotate":
                        if (commandParts.length != 2) {
                            System.out.println("Usage: rotate <Direction>");
                            break;
                        }
                        Direction direction = Direction.fromString(commandParts[1]);
                        invoker.setCommand(new RotateCommand(satellite, direction));
                        invoker.executeCommand();
                        break;
                    case "activatepanels":
                        invoker.setCommand(new ActivatePanelsCommand(satellite));
                        invoker.executeCommand();
                        break;
                    case "deactivatepanels":
                        invoker.setCommand(new DeactivatePanelsCommand(satellite));
                        invoker.executeCommand();
                        break;
                    case "collectdata":
                        invoker.setCommand(new CollectDataCommand(satellite));
                        invoker.executeCommand();
                        break;
                    case "status":
                        System.out.println(satellite);
                        break;
                    default:
                        System.out.println("Unknown command. Type 'exit' to quit.");
                }
            } catch (SatelliteStateException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                System.out.println(e.getMessage());
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An unexpected error occurred.", e);
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
        logger.log(Level.INFO, "Satellite Command System Ended.");
    }

    private static void showMenu() {
        System.out.println("\n=== Satellite Command System ===");
        System.out.println("Enter a command:");
        System.out.println("- rotate <Direction>");
        System.out.println("- activatePanels");
        System.out.println("- deactivatePanels");
        System.out.println("- collectData");
        System.out.println("- status");
        System.out.println("- exit");
        System.out.print("Command: ");
    }
}
