package cashmachine;

import cashmachine.exception.NotEnoughMoneyException;
import java.util.*;

public class CurrencyManipulator {
    private final String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public boolean hasMoney() {
        boolean result = true;
        if (denominations.isEmpty()) {
            result = false;
        }
        else {
            int count = 0;
            for (Map.Entry<Integer, Integer> map : denominations.entrySet()) {
                if (map.getValue() == 0) {
                    count++;
                }
            }
            if (count == denominations.size()) {
                result = false;
            }
        }
        return result;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        }
        else { denominations.put(denomination, count); }
    }

    public int getTotalAmount() {
        int amount = 0;
        for (Map.Entry<Integer, Integer> money : denominations.entrySet()) {
            amount = amount + (money.getKey() * money.getValue());
        }
        return amount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
        ArrayList<Integer> currentDenominations = new ArrayList<>(denominations.keySet());
        currentDenominations.sort(Collections.reverseOrder());
        Map<Integer, Integer> copyDenomination = new HashMap<>(denominations);

        for (Integer currentDenomination : currentDenominations) {
            if (expectedAmount > 0) {
                int value = currentDenomination;
                if (expectedAmount >= value) {
                    int count = expectedAmount / value;
                    if (denominations.get(value) > count) {
                        result.put(value, count);
                        denominations.put(value, denominations.get(value) - count);
                        expectedAmount = expectedAmount - count * value;
                    } else {
                        result.put(value, denominations.get(value));
                        expectedAmount = expectedAmount - denominations.get(value) * value;
                        denominations.remove(value);
                    }
                }
            } else break;
        }
        if (expectedAmount != 0) {
            denominations = copyDenomination;
            throw new NotEnoughMoneyException();
        }
        return result;
    }

}
