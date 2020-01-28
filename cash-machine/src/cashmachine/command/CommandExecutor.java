package cashmachine.command;

import cashmachine.Operation;
import cashmachine.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    public static final Map<Operation, Command> map;

    private CommandExecutor() {}

    static {
        map = new HashMap<>();
        map.put(Operation.LOGIN, new LoginCommand());
        map.put(Operation.INFO, new InfoCommand());
        map.put(Operation.DEPOSIT, new DepositCommand());
        map.put(Operation.WITHDRAW, new WithdrawCommand());
        map.put(Operation.EXIT, new ExitCommand());
    }

    public static void execute(Operation operation) throws InterruptOperationException {
        map.get(operation).execute();
    }
}
