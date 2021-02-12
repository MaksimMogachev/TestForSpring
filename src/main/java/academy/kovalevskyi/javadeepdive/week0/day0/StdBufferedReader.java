package academy.kovalevskyi.javadeepdive.week0.day0;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class StdBufferedReader implements Closeable {

  private final Reader reader;
  private char[] cb;
  private int index = 0;

  private static final int defaultCharBufferSize = 8192;

  public static void main(String[] args) {
    try {
      StdBufferedReader stdBufferedReader = new StdBufferedReader(new FileReader("test.txt"));
      System.out.println(stdBufferedReader.readLine());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public StdBufferedReader(Reader reader, int bufferSize) {
    if (bufferSize <= 0)
      throw new IllegalArgumentException("Buffer size <= 0");
    this.reader = reader;
    cb = new char[bufferSize];
  }

  public StdBufferedReader(Reader reader) {
    this(reader, defaultCharBufferSize);
  }


  // Returns true if there is something to read from the reader.
  // False if nothing is there
  public boolean hasNext() throws IOException {
    if (!reader.ready()) {
      char[] check = this.readLine();
      if (check != null)
        if (check.length > 0) {
          return true;
        } else {
          return false;
        }
    }
    return reader.ready();
  }

  // Returns a line (everything till the next line)
  public char[] readLine() throws IOException {
    char[] result = null;

    do {
      reader.read(cb, 0, cb.length);
      for (int i = this.index; i < cb.length; i++) {
        if (cb[i] == '\n' || cb[i] == (char)0) {
          result = Arrays.copyOfRange(cb, this.index, i);
          this.index = i+1;
          return result;
        } else {
          continue;
        }
      }
    } while (hasNext());
    return result;
  }


  // Closing
  @Override
  public void close() throws IOException {
    if (reader != null)
      reader.close();
  }
}
