package academy.kovalevskyi.javadeepdive.week0.day2;
import java.io.*;
import java.util.ArrayList;

public class CSVHelper {
  public static CSV parseFile(Reader reader) throws IOException {
    return parseFile(reader, false, ',');
  }

  public static CSV parseFile(Reader reader, boolean withHeader, char delimiter) throws IOException {
    ArrayList<String> resultWithHeader = new ArrayList<>();
    StringBuilder result = new StringBuilder();
    String line;
    int currentLineOfArray = 0;
    String[] header = null;

    try(BufferedReader br = new BufferedReader(reader)) {
      while(br.ready()) {
        line = br.readLine();
        resultWithHeader.add(line);
      }
    }
    catch(IOException ex) {
      System.out.println(ex.getMessage());
    }

    if (withHeader) {
      header = resultWithHeader.get(currentLineOfArray).split(" ");
      currentLineOfArray++;
    }

    for (int i = currentLineOfArray; i < resultWithHeader.size(); i++) {
      result.append(resultWithHeader.get(i));
    }

    String[] temporaryValue = result.toString().split(Character.toString(delimiter));
    String[][] values = new String[temporaryValue.length][];

    for (int i = 0; i < temporaryValue.length; i++) {
      values[i] = temporaryValue[i].split(" ");
    }

    return new CSV(header, values);
  }

  public static void writeCsv(Writer writer, CSV csv, char delimiter) throws IOException {

    try (writer){
      if (csv.withHeader()) {
        String[] header = csv.header();

        for (int i = 0; i < header.length - 1; i++) {
          writer.append(header[i]).append(",");
        }
        writer.append(header[header.length - 1]);
        writer.append("\n");
      }

      String[][] values = csv.values();

      for (String[] value : values) {
        for (int j = 0; j < value.length - 1; j++) {
          writer.append(value[j]).append(",");
        }
        writer.append(value[value.length - 1]);
        writer.append("\n");
      }
    }
  }
}
