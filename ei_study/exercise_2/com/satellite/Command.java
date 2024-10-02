package com.satellite;

public interface Command {
    void execute() throws SatelliteStateException;
}
