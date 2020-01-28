package shortener;

import shortener.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
  public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
    Set<Long> ids = new HashSet<>();
    for (String s : strings) {
      ids.add(shortener.getId(s));
    }
    return ids;
  }

  public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
    Set<String> strings = new HashSet<>();
    for (Long key : keys) {
      strings.add(shortener.getString(key));
    }
    return strings;
  }

  public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
    Helper.printMessage(strategy.getClass().getSimpleName());
    Set<String> strings = new HashSet<>();
    for (long i = 0; i < elementsNumber; i++) {
      strings.add(Helper.generateRandomString());
    }
    Shortener shortener = new Shortener(strategy);
    Long time1 = new Date().getTime();
    Set<Long> keys = getIds(shortener, strings);
    Long time2 = new Date().getTime();
    long duration = time2 - time1;
    Helper.printMessage(Long.toString(duration));
    Long time3 = new Date().getTime();
    Set<String> stringsSet = getStrings(shortener, keys);
    Long time4 = new Date().getTime();
    long duration2 = time4 - time3;
    Helper.printMessage(Long.toString(duration2));
    if (stringsSet.equals(strings)) Helper.printMessage("Тест пройден.");
    else Helper.printMessage("Тест не пройден.");
  }

  public static void main(String[] args) {
    Main.testStrategy(new HashMapStorageStrategy(), 10000L);
    Main.testStrategy(new OurHashMapStorageStrategy(), 10000L);
    Main.testStrategy(new FileStorageStrategy(), 10L);
    Main.testStrategy(new OurHashBiMapStorageStrategy(), 10000L);
    Main.testStrategy(new HashBiMapStorageStrategy(), 10000L);
    Main.testStrategy(new DualHashBidiMapStorageStrategy(), 10000L);
  }
}
