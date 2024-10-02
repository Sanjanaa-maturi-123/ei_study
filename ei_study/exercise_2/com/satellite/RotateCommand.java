package com.satellite;

public class RotateCommand implements Command {
    private Satellite satellite;
    private Direction direction;

    public RotateCommand(Satellite satellite, Direction direction) {
        this.satellite = satellite;
        this.direction = direction;
    }

    @Override
    public void execute() throws SatelliteStateException {
        satellite.rotate(direction);
    }
}
