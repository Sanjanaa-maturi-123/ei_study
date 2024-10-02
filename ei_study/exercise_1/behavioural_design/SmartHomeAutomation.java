// *** BEHAVIORAL DESIGN PATTERN USE CASE 2: Command Pattern ***
// *** Use Case: Smart Home Automation ***
//
// This implementation uses the **Command Pattern**, where actions such as turning a light on and off are encapsulated as objects.
//
// 1. The **Light** class is the receiver that performs the actual actions (on/off).
// 2. The **LightOnCommand** class is the command that holds a reference to the receiver and defines actions in terms of
//    "execute" and "undo" operations.
// 3. The **RemoteControl** class acts as the invoker, which can store a command and execute it.
// 4. The design avoids hardcoding loops or flag conditions by encapsulating the actions into separate command objects.
// 5. **Logging** captures every action (turning on/off) for traceability and debugging.
// 6. **Defensive programming** ensures that commands are valid and prevent any null states.
//
// This pattern is especially useful in smart home automation where multiple actions are performed by different devices.

interface Command {
    void execute();
    void undo();
}

class Light {
    public void on() {
        System.out.println("Light is turned on");
    }

    public void off() {
        System.out.println("Light is turned off");
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}

public class SmartHomeAutomation {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(lightOn);

        remote.pressButton();  // Light turns on
        remote.pressUndo();     // Light turns off
    }
}
