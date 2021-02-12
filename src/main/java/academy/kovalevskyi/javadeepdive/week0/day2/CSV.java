package academy.kovalevskyi.javadeepdive.week0.day2;

import java.util.Arrays;
import java.util.Objects;

public record CSV(String[] header, String[][] values) {

  public static class Builder {

    private String[] header;
    private String[][] values;

    public Builder header(String[] header) {
      this.header = header;
      return this;
    }

    public Builder values(String[][] values) {
      this.values = values;
      return this;
    }

    public CSV build() {
      return new CSV(this.header, this.values);
    }
  }

  public boolean withHeader() {
    return this.header != null && this.header.length > 0;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof CSV)) {
      return false;
    }

    CSV other = (CSV) o;
    if (!Arrays.equals(this.header, other.header)) {
      return false;
    }

    return Arrays.deepEquals(this.values, other.values);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }
}
