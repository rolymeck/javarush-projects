package shortener.strategies;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorageStrategy implements StorageStrategy {
  private final HashMap<Long, String> data = new HashMap<>();

  @Override
  public boolean containsKey(Long key) {
    return data.containsKey(key);
  }

  @Override
  public boolean containsValue(String value) {
    return data.containsValue(value);
  }

  @Override
  public void put(Long key, String value) {
    data.put(key, value);
  }

  @Override
  public Long getKey(String value) {
    Long res = null;
    for (Map.Entry<Long, String> entry : data.entrySet()) {
      if (entry.getValue().equals(value)) {
        res = entry.getKey();
      }
    }
    return res;
  }

  @Override
  public String getValue(Long key) {
    return data.get(key);
  }
}
