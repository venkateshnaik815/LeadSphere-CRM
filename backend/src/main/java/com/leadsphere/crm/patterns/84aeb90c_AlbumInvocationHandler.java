package com.leadsphere.crm.patterns;

import com.iluwatar.dynamicproxy.tinyrestclient.TinyRestClient;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.http.HttpClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlbumInvocationHandler implements InvocationHandler {

  private TinyRestClient restClient;

  public AlbumInvocationHandler(String baseUrl, HttpClient httpClient) {
    this.restClient = new TinyRestClient(baseUrl, httpClient);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    LOGGER.info(
        "===== Calling the method {}.{}()",
        method.getDeclaringClass().getSimpleName(),
        method.getName());

    return restClient.send(method, args);
  }
}
