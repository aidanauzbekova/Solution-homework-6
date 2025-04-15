import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SupportHandler chain = SupportChainBuilder.build(Arrays.asList(
                new FAQBotHandler(),
                new JuniorSupportHandler(),
                new SeniorSupportHandler()
        ));

        String[] issues = {"password_reset", "refund_request", "account_ban", "unknown_bug"};
        for (String issue : issues) {
            try {
                chain.handle(issue);
            } catch (UnhandledIssueException e) {
                System.out.println(e.getMessage());
            }
        }


        Light light = new Light();
        Thermostat thermostat = new Thermostat();

        Command lightCmd = new TurnOnLightCommand(light);
        Command thermoCmd = new SetThermostatCommand(thermostat, 22);
        Command goodnight = new MacroCommand(Arrays.asList(
                new TurnOnLightCommand(light),
                new SetThermostatCommand(thermostat, 18)
        ));

        SmartHomeRemoteControl remote = new SmartHomeRemoteControl();
        remote.setCommand("1", lightCmd);
        remote.setCommand("2", thermoCmd);
        remote.setCommand("3", goodnight);

        Scanner scan = new Scanner(System.in);
        String input;

        System.out.println("\n-- Smart Home CLI --");
        do {
            System.out.println("\nChoose option:");
            System.out.println("1. Turn on light");
            System.out.println("2. Set thermostat to 22°C");
            System.out.println("3. Goodnight mode");
            System.out.println("4. Undo");
            System.out.println("5. Redo");
            System.out.println("6. Exit");
            System.out.print("Input: ");
            input = scan.nextLine();

            switch (input) {
                case "1": case "2": case "3": remote.pressButton(input); break;
                case "4": remote.undoButton(); break;
                case "5": remote.redoButton(); break;
                case "6": System.out.println("Exiting..."); break;
                default: System.out.println("Invalid input");
            }

        } while (!input.equals("6"));
    }
}