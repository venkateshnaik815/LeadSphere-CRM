package com.leadsphere.crm.patterns;

import java.util.Locale;
import lombok.Setter;

@Setter
public class BusinessLookup {

  private NetflixService netflixService;

  private YouTubeService youTubeService;

  public VideoStreamingService getBusinessService(String movie) {
    if (movie.toLowerCase(Locale.ROOT).contains("die hard")) {
      return netflixService;
    } else {
      return youTubeService;
    }
  }
}
