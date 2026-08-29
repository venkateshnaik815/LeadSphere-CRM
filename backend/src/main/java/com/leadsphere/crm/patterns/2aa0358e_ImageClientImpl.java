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
public class ImageClientImpl implements ImageClient {

  @Override
  public String getImagePath() {

    var httpClient = HttpClient.newHttpClient();
    var httpGet =
        HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:50005/image-path")).build();

    try {
      LOGGER.info("Sending request to fetch image path");
      var httpResponse = httpClient.send(httpGet, BodyHandlers.ofString());
      logResponse(httpResponse);
      return httpResponse.body();
    } catch (IOException ioe) {
      LOGGER.error("Failure occurred while getting image path", ioe);
    } catch (InterruptedException ie) {
      LOGGER.error("Failure occurred while getting image path", ie);
      Thread.currentThread().interrupt();
    }

    return null;
  }

  private void logResponse(HttpResponse<String> httpResponse) {
    if (isSuccessResponse(httpResponse.statusCode())) {
      LOGGER.info("Image path received successfully");
    } else {
      LOGGER.warn("Image path request failed");
    }
  }

  private boolean isSuccessResponse(int responseCode) {
    return responseCode >= 200 && responseCode <= 299;
  }
}
