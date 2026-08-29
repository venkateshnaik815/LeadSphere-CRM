package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    // eagerly initialized multiton
    LOGGER.info("Printing out eagerly initialized multiton contents");
    LOGGER.info("KHAMUL={}", Nazgul.getInstance(NazgulName.KHAMUL));
    LOGGER.info("MURAZOR={}", Nazgul.getInstance(NazgulName.MURAZOR));
    LOGGER.info("DWAR={}", Nazgul.getInstance(NazgulName.DWAR));
    LOGGER.info("JI_INDUR={}", Nazgul.getInstance(NazgulName.JI_INDUR));
    LOGGER.info("AKHORAHIL={}", Nazgul.getInstance(NazgulName.AKHORAHIL));
    LOGGER.info("HOARMURATH={}", Nazgul.getInstance(NazgulName.HOARMURATH));
    LOGGER.info("ADUNAPHEL={}", Nazgul.getInstance(NazgulName.ADUNAPHEL));
    LOGGER.info("REN={}", Nazgul.getInstance(NazgulName.REN));
    LOGGER.info("UVATHA={}", Nazgul.getInstance(NazgulName.UVATHA));

    // enum multiton
    LOGGER.info("Printing out enum-based multiton contents");
    LOGGER.info("KHAMUL={}", NazgulEnum.KHAMUL);
    LOGGER.info("MURAZOR={}", NazgulEnum.MURAZOR);
    LOGGER.info("DWAR={}", NazgulEnum.DWAR);
    LOGGER.info("JI_INDUR={}", NazgulEnum.JI_INDUR);
    LOGGER.info("AKHORAHIL={}", NazgulEnum.AKHORAHIL);
    LOGGER.info("HOARMURATH={}", NazgulEnum.HOARMURATH);
    LOGGER.info("ADUNAPHEL={}", NazgulEnum.ADUNAPHEL);
    LOGGER.info("REN={}", NazgulEnum.REN);
    LOGGER.info("UVATHA={}", NazgulEnum.UVATHA);
  }
}
