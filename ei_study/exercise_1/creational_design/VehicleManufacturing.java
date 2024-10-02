// *** CREATIONAL DESIGN PATTERN USE CASE 2: Factory Pattern ***
// *** Use Case: Vehicle Manufacturing ***
//
// This implementation demonstrates the **Factory Pattern**, where vehicle objects (Car, Bike) are created dynamically based on the input.
//
// 1. The **VehicleFactory** class is responsible for creating different types of vehicles based on the input type.
// 2. It centralizes the object creation process and hides the instantiation logic from the client.
// 3. **Defensive programming** ensures that only valid vehicle types are passed into the factory method.
// 4. **Logging** is used to track which vehicle type is created.
//
// The Factory pattern is useful when the types of objects to be created may evolve in the future, centralizing and abstracting the creation process.

import java.util.logging.Logger;

interface Vehicle {
    void create();
}

class Car implements Vehicle {
    private static final Logger logger = Logger.getLogger(Car.class.getName());

    @Override
    public void create() {
        logger.info("Car is created");
    }
}

class Bike implements Vehicle {
    private static final Logger logger = Logger.getLogger(Bike.class.getName());

    @Override
    public void create() {
        logger.info("Bike is created");
    }
}

class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be null or empty");
        }

        if (type.equalsIgnoreCase("Car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("Bike")) {
            return new Bike();
        } else {
            throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}

public class VehicleManufacturing {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.getVehicle("Car");
        car.create();

        Vehicle bike = VehicleFactory.getVehicle("Bike");
        bike.create();
    }
}
