package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import java.util.Objects;

public abstract class AbstractRequest<T> {
  protected final Csv target;

  protected AbstractRequest(final Csv target) {
    Objects.requireNonNull(target);

    this.target = target;
  }

  protected abstract T execute() throws RequestException;

  // later you can put here any protected methods that required in multiple requests
}