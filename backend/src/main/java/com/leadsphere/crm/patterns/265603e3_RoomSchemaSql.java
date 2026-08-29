package com.leadsphere.crm.patterns;

public final class RoomSchemaSql {

  public static final String CREATE_SCHEMA_SQL =
      "CREATE TABLE ROOMS (ID NUMBER, ROOM_TYPE VARCHAR(100), PRICE INT, BOOKED VARCHAR(100))";
  public static final String DELETE_SCHEMA_SQL = "DROP TABLE ROOMS IF EXISTS";

  private RoomSchemaSql() {}
}
