package academy.kovalevskyi.javadeepdive.week0.day3;

/**
 * Represents a selector to match values in CSV record
 */
public record Selector(String fieldName, String value) {

  /**
   *
   */
  public static class Builder {
    private String fieldName;
    private String value;

    /**
     * @param fieldName the name of CSV column
     * @return this
     */
    public Builder fieldName(final String fieldName) {
      this.fieldName = fieldName;

      return this;
    }

    /**
     * @param value the value of CSV column
     * @return this
     */
    public Builder value(final String value) {
      this.value = value;

      return this;
    }

    public Selector build() {
      return new Selector(this.fieldName, this.value);
    }
  }
}