package com.satellite;

public class CollectDataCommand implements Command {
    private Satellite satellite;

    public CollectDataCommand(Satellite satellite) {
        this.satellite = satellite;
    }

    @Override
    public void execute() throws SatelliteStateException {
        satellite.collectData();
    }
}
