import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class SmartHomeRemoteControl {
    private Map<String, Command> slots = new HashMap<>();
    private Stack<Command> history = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void setCommand(String slot, Command command) {
        slots.put(slot, command);
    }

    public void pressButton(String slot) {
        Command cmd = slots.get(slot);
        if (cmd != null) {
            cmd.execute();
            history.push(cmd);
            redoStack.clear();
        } else {
            System.out.println("[Remote] No command assigned to slot '" + slot + "'");
        }
    }

    public void undoButton() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            last.undo();
            redoStack.push(last);
        } else {
            System.out.println("[Remote] Nothing to undo");
        }
    }

    public void redoButton() {
        if (!redoStack.isEmpty()) {
            Command redo = redoStack.pop();
            redo.execute();
            history.push(redo);
        } else {
            System.out.println("[Remote] Nothing to redo");
        }
    }
}