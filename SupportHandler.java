import java.util.*;

abstract class SupportHandler {
    protected SupportHandler nextHandler;
    protected String name;

    public SupportHandler setNext(SupportHandler handler) {
        this.nextHandler = handler;
        return handler;
    }

    public abstract void handle(String issue) throws UnhandledIssueException;
}

class Logger {
    public static void log(String message) {
        System.out.println("[Logger] " + message);
    }
}

class UnhandledIssueException extends Exception {
    public UnhandledIssueException(String issue) {
        super("Unhandled issue: " + issue);
    }
}

class FAQBotHandler extends SupportHandler {
    public FAQBotHandler() { this.name = "FAQBotHandler"; }

    public void handle(String issue) throws UnhandledIssueException {
        Logger.log(name + " tried " + issue);
        if (issue.equals("password_reset")) {
            System.out.println("[FAQBot] Handled password_reset");
        } else if (nextHandler != null) {
            nextHandler.handle(issue);
        } else {
            throw new UnhandledIssueException(issue);
        }
    }
}

class JuniorSupportHandler extends SupportHandler {
    public JuniorSupportHandler() { this.name = "JuniorSupportHandler"; }

    public void handle(String issue) throws UnhandledIssueException {
        Logger.log(name + " tried " + issue);
        if (issue.equals("refund_request") || issue.equals("billing_issue")) {
            System.out.println("[JuniorSupport] Handled " + issue);
        } else if (nextHandler != null) {
            nextHandler.handle(issue);
        } else {
            throw new UnhandledIssueException(issue);
        }
    }
}

class SeniorSupportHandler extends SupportHandler {
    public SeniorSupportHandler() { this.name = "SeniorSupportHandler"; }

    public void handle(String issue) throws UnhandledIssueException {
        Logger.log(name + " tried " + issue);
        if (issue.equals("account_ban") || issue.equals("data_loss")) {
            System.out.println("[SeniorSupport] Handled " + issue);
        } else {
            System.out.println("[SeniorSupport] Cannot handle " + issue + " — escalate manually");
            throw new UnhandledIssueException(issue);
        }
    }
}