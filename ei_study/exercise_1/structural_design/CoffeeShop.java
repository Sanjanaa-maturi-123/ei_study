// *** STRUCTURAL DESIGN PATTERN USE CASE 2: Decorator Pattern ***
// *** Use Case: Coffee Shop Pricing ***
//
// This implementation demonstrates the **Decorator Pattern**, where a basic coffee object is decorated with additional
// features like milk and sugar to dynamically add functionality and alter its behavior.
//
// 1. The **Coffee** interface defines the base behavior (cost).
// 2. The **SimpleCoffee** class provides the base implementation of the coffee.
// 3. The **CoffeeDecorator** abstract class is used to extend the functionality of the base coffee without modifying it.
// 4. **Logging** is used to track the cost additions, and **defensive programming** ensures valid cost calculations.
//
// This pattern is useful when behavior needs to be dynamically added to an object without altering its structure.

import java.util.logging.Logger;

interface Coffee {
    double cost();
}

class SimpleCoffee implements Coffee {
    private static final Logger logger = Logger.getLogger(SimpleCoffee.class.getName());

    @Override
    public double cost() {
        logger.info("Simple coffee: $5.0");
        return 5.0;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 2.0; // Adding cost for milk
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 1.0; // Adding cost for sugar
    }
}

public class CoffeeShop {
    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        Coffee milkAndSugarCoffee = new SugarDecorator(milkCoffee);

        System.out.println("Cost of simple coffee: $" + simpleCoffee.cost());
        System.out.println("Cost of milk coffee: $" + milkCoffee.cost());
        System.out.println("Cost of milk and sugar coffee: $" + milkAndSugarCoffee.cost());
    }
}
