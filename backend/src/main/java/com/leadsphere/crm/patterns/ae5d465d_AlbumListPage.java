package com.leadsphere.crm.patterns;

import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlPage;

@Slf4j
public class AlbumListPage extends Page {
  private static final String ALBUM_LIST_HTML_FILE = "album-list.html";
  private static final String PAGE_URL = "file:" + AUT_PATH + ALBUM_LIST_HTML_FILE;

  private HtmlPage page;

  public AlbumListPage(WebClient webClient) {
    super(webClient);
  }

  public AlbumListPage navigateToPage() {
    try {
      page = this.webClient.getPage(PAGE_URL);
    } catch (IOException e) {
      LOGGER.error("An error occurred on navigateToPage.", e);
    }
    return this;
  }

  @Override
  public boolean isAt() {
    return "Album List".equals(page.getTitleText());
  }

  public AlbumPage selectAlbum(String albumTitle) {
    // uses XPath to find list of html anchor tags with the class album in it
    var albumLinks = (List<Object>) page.getByXPath("//tr[@class='album']//a");
    for (var anchor : albumLinks) {
      if (((HtmlAnchor) anchor).getTextContent().equals(albumTitle)) {
        try {
          ((HtmlAnchor) anchor).click();
          return new AlbumPage(webClient);
        } catch (IOException e) {
          LOGGER.error("An error occurred on selectAlbum", e);
        }
      }
    }
    throw new IllegalArgumentException("No links with the album title: " + albumTitle);
  }
}
