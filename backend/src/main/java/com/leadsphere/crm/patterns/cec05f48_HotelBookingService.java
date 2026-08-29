package com.leadsphere.crm.patterns;

public class HotelBookingService extends Service<String> {
  @Override
  public String getName() {
    return "booking a Hotel";
  }

  @Override
  public ChapterResult<String> rollback(String value) {
    if (value.equals("crashed_order")) {
      LOGGER.info(
          "The Rollback for a chapter '{}' has been started. "
              + "The data {} has been failed.The saga has been crashed.",
          getName(),
          value);

      return ChapterResult.failure(value);
    }

    LOGGER.info(
        "The Rollback for a chapter '{}' has been started. "
            + "The data {} has been rollbacked successfully",
        getName(),
        value);

    return super.rollback(value);
  }
}
