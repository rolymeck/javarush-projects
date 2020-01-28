package cashmachine;

import java.util.*;

public class CurrencyManipulatorFactory {
    private static final CurrencyManipulatorFactory ourInstance = new CurrencyManipulatorFactory();
    private static final Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {}

    public static CurrencyManipulatorFactory getInstance()
    {
        return ourInstance;
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        for (Map.Entry<String, CurrencyManipulator> manipulator : map.entrySet()) {
            if (manipulator.getValue().getCurrencyCode().equals(currencyCode)) {
                return manipulator.getValue();
            }
        }
        CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode);
        map.put(currencyCode, manipulator);
        return manipulator;
    }

    public static Collection getAllCurrencyManipulators() {
        return map.values();
    }
}
