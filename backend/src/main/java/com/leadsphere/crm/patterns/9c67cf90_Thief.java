import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Thief {

  protected void steal() {
    LOGGER.info("Steal valuable items");
  }

  protected void doNothing() {
    LOGGER.info("Pretend nothing happened and just leave");
  }
}
