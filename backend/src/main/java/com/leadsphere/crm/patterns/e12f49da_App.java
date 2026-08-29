import abstractextensions.CommanderExtension;
import abstractextensions.SergeantExtension;
import abstractextensions.SoldierExtension;
import java.util.Optional;
import java.util.function.Function;
import org.slf4j.LoggerFactory;
import units.CommanderUnit;
import units.SergeantUnit;
import units.SoldierUnit;
import units.Unit;

public class App {

  public static void main(String[] args) {

    // Create 3 different units
    var soldierUnit = new SoldierUnit("SoldierUnit1");
    var sergeantUnit = new SergeantUnit("SergeantUnit1");
    var commanderUnit = new CommanderUnit("CommanderUnit1");

    // check for each unit to have an extension
    checkExtensionsForUnit(soldierUnit);
    checkExtensionsForUnit(sergeantUnit);
    checkExtensionsForUnit(commanderUnit);
  }

  private static void checkExtensionsForUnit(Unit unit) {
    final var logger = LoggerFactory.getLogger(App.class);

    var name = unit.getName();
    Function<String, Runnable> func = e -> () -> logger.info("{} without {}", name, e);

    var extension = "SoldierExtension";
    Optional.ofNullable(unit.getUnitExtension(extension))
        .map(e -> (SoldierExtension) e)
        .ifPresentOrElse(SoldierExtension::soldierReady, func.apply(extension));

    extension = "SergeantExtension";
    Optional.ofNullable(unit.getUnitExtension(extension))
        .map(e -> (SergeantExtension) e)
        .ifPresentOrElse(SergeantExtension::sergeantReady, func.apply(extension));

    extension = "CommanderExtension";
    Optional.ofNullable(unit.getUnitExtension(extension))
        .map(e -> (CommanderExtension) e)
        .ifPresentOrElse(CommanderExtension::commanderReady, func.apply(extension));
  }
}
