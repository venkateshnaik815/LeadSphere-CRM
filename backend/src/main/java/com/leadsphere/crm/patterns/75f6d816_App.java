package com.leadsphere.crm.patterns;

import java.util.List;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final String H2_DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) throws Exception {

    final var dataSource = createDataSource();
    deleteSchema(dataSource);
    createSchema(dataSource);
    final var dao = new HotelDaoImpl(dataSource);

    // Add rooms
    addRooms(dao);

    // Print room booking status
    getRoomStatus(dao);

    var hotel = new Hotel(dao);

    // Book rooms
    hotel.bookRoom(1);
    hotel.bookRoom(2);
    hotel.bookRoom(3);
    hotel.bookRoom(4);
    hotel.bookRoom(5);
    hotel.bookRoom(6);

    // Cancel booking for a few rooms
    hotel.cancelRoomBooking(1);
    hotel.cancelRoomBooking(3);
    hotel.cancelRoomBooking(5);

    getRoomStatus(dao);

    deleteSchema(dataSource);
  }

  private static void getRoomStatus(HotelDaoImpl dao) throws Exception {
    try (var customerStream = dao.getAll()) {
      customerStream.forEach((customer) -> LOGGER.info(customer.toString()));
    }
  }

  private static void deleteSchema(DataSource dataSource) throws java.sql.SQLException {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      statement.execute(RoomSchemaSql.DELETE_SCHEMA_SQL);
    }
  }

  private static void createSchema(DataSource dataSource) throws Exception {
    try (var connection = dataSource.getConnection();
        var statement = connection.createStatement()) {
      statement.execute(RoomSchemaSql.CREATE_SCHEMA_SQL);
    } catch (Exception e) {
      throw new Exception(e.getMessage(), e);
    }
  }

  private static DataSource createDataSource() {
    var dataSource = new JdbcDataSource();
    dataSource.setUrl(H2_DB_URL);
    return dataSource;
  }

  private static void addRooms(HotelDaoImpl hotelDao) throws Exception {
    for (var room : generateSampleRooms()) {
      hotelDao.add(room);
    }
  }

  private static List<Room> generateSampleRooms() {
    final var room1 = new Room(1, "Single", 50, false);
    final var room2 = new Room(2, "Double", 80, false);
    final var room3 = new Room(3, "Queen", 120, false);
    final var room4 = new Room(4, "King", 150, false);
    final var room5 = new Room(5, "Single", 50, false);
    final var room6 = new Room(6, "Double", 80, false);
    return List.of(room1, room2, room3, room4, room5, room6);
  }
}
