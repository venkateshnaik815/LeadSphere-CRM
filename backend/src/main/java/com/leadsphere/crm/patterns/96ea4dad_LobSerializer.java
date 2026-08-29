package com.leadsphere.crm.patterns;

import com.iluwatar.slob.dbservice.DatabaseService;
import com.iluwatar.slob.lob.Forest;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public abstract class LobSerializer implements Serializable, Closeable {

  private final transient DatabaseService databaseService;

  protected LobSerializer(String dataTypeDb) throws SQLException {
    databaseService = new DatabaseService(dataTypeDb);
    databaseService.startupService();
  }

  public abstract Object serialize(Forest toSerialize)
      throws ParserConfigurationException, TransformerException, IOException;

  public int persistToDb(int id, String name, Object object) throws SQLException {
    databaseService.insert(id, name, object);
    return id;
  }

  public Object loadFromDb(int id, String columnName) throws SQLException {
    return databaseService.select(id, columnName);
  }

  public abstract Forest deSerialize(Object toDeserialize)
      throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException;

  @Override
  public void close() {
    try {
      databaseService.shutDownService();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
