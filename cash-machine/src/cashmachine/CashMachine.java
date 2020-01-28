package cashmachine;

import cashmachine.command.CommandExecutor;
import cashmachine.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = "cashmachine.resources.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            CommandExecutor.execute(Operation.LOGIN);
            Operation currentOperation;
            do {
                currentOperation = ConsoleHelper.askOperation();
                CommandExecutor.execute(currentOperation);
            }
            while (currentOperation != Operation.EXIT);
        }
        catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("Goodbye!");
        }
    }
}
