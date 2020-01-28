package cashmachine.command;

import cashmachine.CashMachine;
import cashmachine.ConsoleHelper;
import cashmachine.CurrencyManipulator;
import cashmachine.CurrencyManipulatorFactory;
import cashmachine.exception.InterruptOperationException;
import cashmachine.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String CurrencyCode=ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator= CurrencyManipulatorFactory.getManipulatorByCurrencyCode(CurrencyCode);
        int amount;

        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            amount=Integer.parseInt(ConsoleHelper.readString());
            if (amount < 0) ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            if(manipulator.isAmountAvailable(amount))break;
            else ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
        }
        try {
            Map<Integer, Integer> map=manipulator.withdrawAmount(amount);
            for (Map.Entry<Integer, Integer> pair :map.entrySet())
                ConsoleHelper.writeMessage("\t"+pair.getKey()+" - "+pair.getValue());

            ConsoleHelper.writeMessage(res.getString("success.format"));
        }
        catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage(res.getString("not.enough.money"));
        }

    }
}
