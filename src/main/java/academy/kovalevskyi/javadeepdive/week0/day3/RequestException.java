package academy.kovalevskyi.javadeepdive.week0.day3;

public class RequestException extends Exception {
  public RequestException(String message) {
    super(message);
  }

  public RequestException() {
    super("Something went wrong... Check your query request");
  }
}