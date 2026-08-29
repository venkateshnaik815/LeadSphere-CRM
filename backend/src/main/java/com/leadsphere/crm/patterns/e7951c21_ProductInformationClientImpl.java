package com.leadsphere.crm.patterns;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductInformationClientImpl implements ProductInformationClient {

  @Override
  public String getProductTitle() {
    var request =
        HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:51515/information"))
            .build();
    var client = HttpClient.newHttpClient();
    try {
      var httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
      return httpResponse.body();
    } catch (IOException ioe) {
      LOGGER.error("IOException Occurred", ioe);
    } catch (InterruptedException ie) {
      LOGGER.error("InterruptedException Occurred", ie);
      Thread.currentThread().interrupt();
    }
    return null;
  }
}
