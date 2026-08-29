package com.leadsphere.crm.patterns;

import java.lang.reflect.Proxy;
import java.net.http.HttpClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  static final String REST_API_URL = "https://jsonplaceholder.typicode.com";

  private String baseUrl;
  private HttpClient httpClient;
  private AlbumService albumServiceProxy;

  public App(String baseUrl, HttpClient httpClient) {
    this.baseUrl = baseUrl;
    this.httpClient = httpClient;
  }

  public static void main(String[] args) {
    App app = new App(App.REST_API_URL, HttpClient.newHttpClient());
    app.createDynamicProxy();
    app.callMethods();
  }

  public void createDynamicProxy() {
    AlbumInvocationHandler albumInvocationHandler = new AlbumInvocationHandler(baseUrl, httpClient);

    albumServiceProxy =
        (AlbumService)
            Proxy.newProxyInstance(
                App.class.getClassLoader(),
                new Class<?>[] {AlbumService.class},
                albumInvocationHandler);
  }

  public void callMethods() {
    int albumId = 17;
    int userId = 3;

    var albums = albumServiceProxy.readAlbums();
    albums.forEach(album -> LOGGER.info("{}", album));

    var album = albumServiceProxy.readAlbum(albumId);
    LOGGER.info("{}", album);

    var newAlbum =
        albumServiceProxy.createAlbum(Album.builder().title("Big World").userId(userId).build());
    LOGGER.info("{}", newAlbum);

    var editAlbum =
        albumServiceProxy.updateAlbum(
            albumId, Album.builder().title("Green Valley").userId(userId).build());
    LOGGER.info("{}", editAlbum);

    var removedAlbum = albumServiceProxy.deleteAlbum(albumId);
    LOGGER.info("{}", removedAlbum);
  }
}
