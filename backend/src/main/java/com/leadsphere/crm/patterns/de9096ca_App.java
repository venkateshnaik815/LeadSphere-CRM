package com.leadsphere.crm.patterns;

import com.iluwatar.slob.lob.Animal;
import com.iluwatar.slob.lob.Forest;
import com.iluwatar.slob.lob.Plant;
import com.iluwatar.slob.serializers.BlobSerializer;
import com.iluwatar.slob.serializers.ClobSerializer;
import com.iluwatar.slob.serializers.LobSerializer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class App {

  public static final String CLOB = "CLOB";
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) throws SQLException {
    Forest forest = createForest();
    LobSerializer serializer = createLobSerializer(args);
    executeSerializer(forest, serializer);
  }

  private static LobSerializer createLobSerializer(String[] args) throws SQLException {
    LobSerializer serializer;
    if (args.length > 0 && Objects.equals(args[0], CLOB)) {
      serializer = new ClobSerializer();
    } else {
      serializer = new BlobSerializer();
    }
    return serializer;
  }

  private static Forest createForest() {
    Plant grass = new Plant("Grass", "Herb");
    Plant oak = new Plant("Oak", "Tree");

    Animal zebra = new Animal("Zebra", Set.of(grass), Collections.emptySet());
    Animal buffalo = new Animal("Buffalo", Set.of(grass), Collections.emptySet());
    Animal lion = new Animal("Lion", Collections.emptySet(), Set.of(zebra, buffalo));

    return new Forest("Amazon", Set.of(lion, buffalo, zebra), Set.of(grass, oak));
  }

  private static void executeSerializer(Forest forest, LobSerializer lobSerializer) {
    try (LobSerializer serializer = lobSerializer) {

      Object serialized = serializer.serialize(forest);
      int id = serializer.persistToDb(1, forest.getName(), serialized);

      Object fromDb = serializer.loadFromDb(id, Forest.class.getSimpleName());
      Forest forestFromDb = serializer.deSerialize(fromDb);

      LOGGER.info(forestFromDb.toString());
    } catch (SQLException
        | IOException
        | TransformerException
        | ParserConfigurationException
        | SAXException
        | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
