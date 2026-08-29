package com.leadsphere.crm.patterns;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlNumberInput;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlSelect;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTextInput;

@Slf4j
public class AlbumPage extends Page {
  private static final String ALBUM_PAGE_HTML_FILE = "album-page.html";
  private static final String PAGE_URL = "file:" + AUT_PATH + ALBUM_PAGE_HTML_FILE;

  private HtmlPage page;

  public AlbumPage(WebClient webClient) {
    super(webClient);
  }

  public AlbumPage navigateToPage() {
    try {
      page = this.webClient.getPage(PAGE_URL);
    } catch (IOException e) {
      LOGGER.error("An error occurred on navigateToPage.", e);
    }
    return this;
  }

  @Override
  public boolean isAt() {
    return "Album Page".equals(page.getTitleText());
  }

  public AlbumPage changeAlbumTitle(String albumTitle) {
    var albumTitleInputTextField = (HtmlTextInput) page.getElementById("albumTitle");
    albumTitleInputTextField.setText(albumTitle);
    return this;
  }

  public AlbumPage changeArtist(String artist) {
    var artistInputTextField = (HtmlTextInput) page.getElementById("albumArtist");
    artistInputTextField.setText(artist);
    return this;
  }

  public AlbumPage changeAlbumYear(int year) {
    var albumYearSelectOption = (HtmlSelect) page.getElementById("albumYear");
    var yearOption = albumYearSelectOption.getOptionByValue(Integer.toString(year));
    albumYearSelectOption.setSelectedAttribute(yearOption, true);
    return this;
  }

  public AlbumPage changeAlbumRating(String albumRating) {
    var albumRatingInputTextField = (HtmlTextInput) page.getElementById("albumRating");
    albumRatingInputTextField.setText(albumRating);
    return this;
  }

  public AlbumPage changeNumberOfSongs(int numberOfSongs) {
    var numberOfSongsNumberField = (HtmlNumberInput) page.getElementById("numberOfSongs");
    numberOfSongsNumberField.setText(Integer.toString(numberOfSongs));
    return this;
  }

  public AlbumListPage cancelChanges() {
    var cancelButton = (HtmlSubmitInput) page.getElementById("cancelButton");
    try {
      cancelButton.click();
    } catch (IOException e) {
      LOGGER.error("An error occurred on cancelChanges.", e);
    }
    return new AlbumListPage(webClient);
  }

  public AlbumPage saveChanges() {
    var saveButton = (HtmlSubmitInput) page.getElementById("saveButton");
    try {
      saveButton.click();
    } catch (IOException e) {
      LOGGER.error("An error occurred on saveChanges.", e);
    }
    return this;
  }
}
