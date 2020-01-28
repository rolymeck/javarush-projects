package cashmachine.command;

import cashmachine.CashMachine;
import cashmachine.ConsoleHelper;
import cashmachine.CurrencyManipulatorFactory;
import cashmachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        try {
            String curCode = null;
            String[] str = null;
            try {
                curCode = ConsoleHelper.askCurrencyCode();
                str = ConsoleHelper.getValidTwoDigits(curCode);
            }
            catch (InterruptOperationException e) {}
            CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curCode).addAmount(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            ConsoleHelper.writeMessage(res.getString("success.format"));
        } catch (NumberFormatException ex) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
