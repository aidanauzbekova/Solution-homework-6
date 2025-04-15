class Thermostat {
    private int temperature = 20;

    public void setTemperature(int temp) {
        System.out.println("[Thermostat] Setting temperature to " + temp + "°C");
        temperature = temp;
    }

    public void revertTemperature(int previousTemp) {
        System.out.println("[Thermostat] Reverting to previous temperature: " + previousTemp + "°C");
        temperature = previousTemp;
    }

    public int getTemperature() { return temperature; }
}