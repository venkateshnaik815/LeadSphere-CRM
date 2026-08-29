import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Guard implements Permission {

  protected void enter() {
    LOGGER.info("You can enter");
  }
}
