import java.util.List;

interface Command {
    void execute();
    void undo();
}


class TurnOnLightCommand implements Command {
    private Light light;
    public TurnOnLightCommand(Light light) { this.light = light; }
    public void execute() { light.turnOn(); }
    public void undo() { light.turnOff(); }
}

class SetThermostatCommand implements Command {
    private Thermostat thermostat;
    private int temperature;
    private int previousTemperature = 20;

    public SetThermostatCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }

    public void execute() {
        previousTemperature = thermostat.getTemperature();
        thermostat.setTemperature(temperature);
    }

    public void undo() {
        thermostat.revertTemperature(previousTemperature);
    }
}

class MacroCommand implements Command {
    private List<Command> commands;

    public MacroCommand(List<Command> commands) {
        this.commands = commands;
    }

    public void execute() {
        for (Command cmd : commands) cmd.execute();
    }

    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) commands.get(i).undo();
    }
}