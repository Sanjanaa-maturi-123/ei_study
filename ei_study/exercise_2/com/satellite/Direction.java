package com.satellite;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    public static Direction fromString(String direction) throws SatelliteStateException {
        switch (direction.toLowerCase()) {
            case "north":
                return NORTH;
            case "south":
                return SOUTH;
            case "east":
                return EAST;
            case "west":
                return WEST;
            default:
                throw new SatelliteStateException("Invalid direction: " + direction);
        }
    }
}
