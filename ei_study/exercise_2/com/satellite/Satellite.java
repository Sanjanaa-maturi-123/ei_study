package com.satellite;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Satellite {
    private static final Logger logger = LoggerSingleton.getInstance();

    private Direction orientation;
    private boolean solarPanelsActive;
    private int dataCollected;

    public Satellite() {
        this.orientation = Direction.NORTH;
        this.solarPanelsActive = false;
        this.dataCollected = 0;
    }

    public void rotate(Direction direction) throws SatelliteStateException {
        if (direction == null) {
            throw new SatelliteStateException("Invalid direction provided.");
        }
        this.orientation = direction;
        logger.log(Level.INFO, "Satellite rotated to " + direction);
        System.out.println("Satellite rotated to " + direction);
    }

    public void activatePanels() {
        this.solarPanelsActive = true;
        logger.log(Level.INFO, "Solar panels activated.");
        System.out.println("Solar panels activated.");
    }

    public void deactivatePanels() {
        this.solarPanelsActive = false;
        logger.log(Level.INFO, "Solar panels deactivated.");
        System.out.println("Solar panels deactivated.");
    }

    public void collectData() throws SatelliteStateException {
        if (!solarPanelsActive) {
            throw new SatelliteStateException("Cannot collect data. Solar panels are inactive.");
        }
        this.dataCollected += 10;
        logger.log(Level.INFO, "Data collected. Total data: " + dataCollected + " units.");
        System.out.println("Data collected. Total data: " + dataCollected + " units.");
    }

    @Override
    public String toString() {
        return String.format("Orientation: %s\nSolar Panels: %s\nData Collected: %d units",
                orientation, solarPanelsActive ? "Active" : "Inactive", dataCollected);
    }
}
