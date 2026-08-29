package com.leadsphere.crm.patterns;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class DisplayedAlbums {
  private final List<Album> albums;

  public DisplayedAlbums() {
    this.albums = new ArrayList<>();
  }

  public void addAlbums(
      final String title, final String artist, final boolean isClassical, final String composer) {
    if (isClassical) {
      this.albums.add(new Album(title, artist, true, composer));
    } else {
      this.albums.add(new Album(title, artist, false, ""));
    }
  }
}
