package com.leadsphere.crm.patterns;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PriceClientImpl implements PriceClient {

  @Override
  public String getPrice() {
    var httpClient = HttpClient.newHttpClient();
    var httpGet =
        HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:50006/price")).build();

    try {
      LOGGER.info("Sending request to fetch price info");
      var httpResponse = httpClient.send(httpGet, BodyHandlers.ofString());
      logResponse(httpResponse);
      return httpResponse.body();
    } catch (IOException e) {
      LOGGER.error("Failure occurred while getting price info", e);
    } catch (InterruptedException e) {
      LOGGER.error("Failure occurred while getting price info", e);
      Thread.currentThread().interrupt();
    }

    return null;
  }

  private void logResponse(HttpResponse<String> httpResponse) {
    if (isSuccessResponse(httpResponse.statusCode())) {
      LOGGER.info("Price info received successfully");
    } else {
      LOGGER.warn("Price info request failed");
    }
  }

  private boolean isSuccessResponse(int responseCode) {
    return responseCode >= 200 && responseCode <= 299;
  }
}
