package shortener.strategies;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
  private Path path;

  public FileBucket() {
    try {
      path = Files.createTempFile(null, null);
      path.toFile().deleteOnExit();
    } catch (IOException e) {
    }
  }

  public long getFileSize() {
    try {
      return Files.size(path);
    } catch (IOException e) {
    }
    return 0L;
  }

  public void putEntry(Entry entry) {
    try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
      oos.writeObject(entry);
    } catch (IOException ignored) {
    }
  }

  public Entry getEntry() {
    if (getFileSize() == 0) return null;
    try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
      return (Entry) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
    }
    return null;
  }

  public void remove() {
    try {
      Files.delete(path);
    } catch (IOException e) {
    }
  }
}
