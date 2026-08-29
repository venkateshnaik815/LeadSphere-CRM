package com.leadsphere.crm.patterns;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PresentationModel {
  private final DisplayedAlbums data;

  private int selectedAlbumNumber;

  private Album selectedAlbum;

  public static DisplayedAlbums albumDataSet() {
    var titleList =
        new String[] {
          "HQ", "The Rough Dancer and Cyclical Night",
          "The Black Light", "Symphony No.5"
        };
    var artistList =
        new String[] {
          "Roy Harper", "Astor Piazzola",
          "The Black Light", "CBSO"
        };
    var isClassicalList = new boolean[] {false, false, false, true};
    var composerList = new String[] {null, null, null, "Sibelius"};

    var result = new DisplayedAlbums();
    for (var i = 1; i <= titleList.length; i++) {
      result.addAlbums(
          titleList[i - 1], artistList[i - 1], isClassicalList[i - 1], composerList[i - 1]);
    }
    return result;
  }

  public PresentationModel(final DisplayedAlbums dataOfAlbums) {
    this.data = dataOfAlbums;
    this.selectedAlbumNumber = 1;
    this.selectedAlbum = this.data.getAlbums().get(0);
  }

  public void setSelectedAlbumNumber(final int albumNumber) {
    LOGGER.info("Change select number from {} to {}", this.selectedAlbumNumber, albumNumber);
    this.selectedAlbumNumber = albumNumber;
    this.selectedAlbum = data.getAlbums().get(this.selectedAlbumNumber - 1);
  }

  public String getTitle() {
    return selectedAlbum.getTitle();
  }

  public void setTitle(final String value) {
    LOGGER.info("Change album title from {} to {}", selectedAlbum.getTitle(), value);
    selectedAlbum.setTitle(value);
  }

  public String getArtist() {
    return selectedAlbum.getArtist();
  }

  public void setArtist(final String value) {
    LOGGER.info("Change album artist from {} to {}", selectedAlbum.getArtist(), value);
    selectedAlbum.setArtist(value);
  }

  public boolean getIsClassical() {
    return selectedAlbum.isClassical();
  }

  public void setIsClassical(final boolean value) {
    LOGGER.info("Change album isClassical from {} to {}", selectedAlbum.isClassical(), value);
    selectedAlbum.setClassical(value);
  }

  public String getComposer() {
    return selectedAlbum.isClassical() ? selectedAlbum.getComposer() : "";
  }

  public void setComposer(final String value) {
    if (selectedAlbum.isClassical()) {
      LOGGER.info("Change album composer from {} to {}", selectedAlbum.getComposer(), value);
      selectedAlbum.setComposer(value);
    } else {
      LOGGER.info("Composer can not be changed");
    }
  }

  public String[] getAlbumList() {
    var result = new String[data.getAlbums().size()];
    for (var i = 0; i < result.length; i++) {
      result[i] = data.getAlbums().get(i).getTitle();
    }
    return result;
  }
}
