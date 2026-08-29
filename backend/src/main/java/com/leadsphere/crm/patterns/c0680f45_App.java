
import org.slf4j.LoggerFactory;

public class App {

  public static void main(String[] args) {
    final var logger = LoggerFactory.getLogger(App.class);
    var guard = new Guard();
    var thief = new Thief();

    //noinspection ConstantConditions
    if (guard instanceof Permission) {
      guard.enter();
    } else {
      logger.info("You have no permission to enter, please leave this area");
    }

    //noinspection ConstantConditions
    if (thief instanceof Permission) {
      thief.steal();
    } else {
      thief.doNothing();
    }
  }
}
