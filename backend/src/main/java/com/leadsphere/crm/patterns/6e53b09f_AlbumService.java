package com.leadsphere.crm.patterns;

import com.iluwatar.dynamicproxy.tinyrestclient.annotation.Body;
import com.iluwatar.dynamicproxy.tinyrestclient.annotation.Delete;
import com.iluwatar.dynamicproxy.tinyrestclient.annotation.Get;
import com.iluwatar.dynamicproxy.tinyrestclient.annotation.Path;
import com.iluwatar.dynamicproxy.tinyrestclient.annotation.Post;
import com.iluwatar.dynamicproxy.tinyrestclient.annotation.Put;
import java.util.List;

public interface AlbumService {

  @Get("/albums")
  List<Album> readAlbums();

  @Get("/albums/{albumId}")
  Album readAlbum(@Path("albumId") Integer albumId);

  @Post("/albums")
  Album createAlbum(@Body Album album);

  @Put("/albums/{albumId}")
  Album updateAlbum(@Path("albumId") Integer albumId, @Body Album album);

  @Delete("/albums/{albumId}")
  Album deleteAlbum(@Path("albumId") Integer albumId);
}
