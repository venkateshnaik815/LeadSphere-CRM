package com.leadsphere.crm.patterns;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {

    useOfLoggedMute();

    useOfMute();
  }

  private static void useOfMute() {
    var out = new ByteArrayOutputStream();
    Mute.mute(() -> out.write("Hello".getBytes()));
  }

  private static void useOfLoggedMute() {
    Optional<Resource> resource = Optional.empty();
    try {
      resource = Optional.of(acquireResource());
      utilizeResource(resource.get());
    } finally {
      resource.ifPresent(App::closeResource);
    }
  }

  private static void closeResource(Resource resource) {
    Mute.loggedMute(resource::close);
  }

  private static void utilizeResource(Resource resource) {
    LOGGER.info("Utilizing acquired resource: {}", resource);
  }

  private static Resource acquireResource() {
    return new Resource() {

      @Override
      public void close() throws IOException {
        throw new IOException("Error in closing resource: " + this);
      }
    };
  }
}
