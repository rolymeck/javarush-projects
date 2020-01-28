package cashmachine;

import cashmachine.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static final ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String answer = null;
        try {
            answer = reader.readLine();
        }
        catch (IOException e) {}
        if (answer.equalsIgnoreCase("EXIT")) {
            ConsoleHelper.writeMessage(res.getString("the.end"));
            throw new InterruptOperationException();
        }
        return answer;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String currencyCode = readString();
            if (currencyCode.length() != 3) {
                writeMessage(res.getString("invalid.data"));
            }
            else { return currencyCode.toUpperCase(); }
        }
    }
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.denomination.and.count.format"));
            String twoDigits = readString();
            String[] digits = twoDigits.split(" ");
            try {
                if (digits.length == 2 && Integer.parseInt(digits[0]) >= 0 && Integer.parseInt(digits[1]) >= 0) {
                    return digits;
                }
                else writeMessage(res.getString("invalid.data"));
            }
            catch (NumberFormatException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }
    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            try {
                writeMessage(res.getString("choose.operation"));
                String operation = readString();
                switch (operation) {
                    case "1":
                        return Operation.getAllowableOperationByOrdinal(1);
                    case "2":
                        return Operation.getAllowableOperationByOrdinal(2);
                    case "3":
                        return Operation.getAllowableOperationByOrdinal(3);
                    case "4":
                        return Operation.getAllowableOperationByOrdinal(4);
                    default:
                        throw new IllegalArgumentException();
                }
            }
            catch (IllegalArgumentException e) {}
        }
    }
}
