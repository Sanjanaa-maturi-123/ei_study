package com.satellite;

public class CommandInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() throws SatelliteStateException {
        if (command != null) {
            command.execute();
        }
    }
}
