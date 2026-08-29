package com.leadsphere.crm.patterns;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public final class AppServlet extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html";
  private String msgPartOne = "<h1>This Server Doesn't Support";
  private String msgPartTwo =
      """
          Requests</h1>
          <h2>Use a GET request with boolean values for the following parameters<h2>
          <h3>'name'</h3>
          <h3>'bus'</h3>
          <h3>'sports'</h3>
          <h3>'sci'</h3>
          <h3>'world'</h3>""";

  private String destination = "newsDisplay.jsp";

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) {
    try {
      RequestDispatcher requestDispatcher = req.getRequestDispatcher(destination);
      ClientPropertiesBean reqParams = new ClientPropertiesBean(req);
      req.setAttribute("properties", reqParams);
      requestDispatcher.forward(req, resp);
    } catch (Exception e) {
      LOGGER.error("Exception occurred GET request processing ", e);
    }
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    resp.setContentType(CONTENT_TYPE);
    try (PrintWriter out = resp.getWriter()) {
      out.println(msgPartOne + " Post " + msgPartTwo);
    } catch (Exception e) {
      LOGGER.error("Exception occurred POST request processing ", e);
    }
  }

  @Override
  public void doDelete(HttpServletRequest req, HttpServletResponse resp) {
    resp.setContentType(CONTENT_TYPE);
    try (PrintWriter out = resp.getWriter()) {
      out.println(msgPartOne + " Delete " + msgPartTwo);
    } catch (Exception e) {
      LOGGER.error("Exception occurred DELETE request processing ", e);
    }
  }

  @Override
  public void doPut(HttpServletRequest req, HttpServletResponse resp) {
    resp.setContentType(CONTENT_TYPE);
    try (PrintWriter out = resp.getWriter()) {
      out.println(msgPartOne + " Put " + msgPartTwo);
    } catch (Exception e) {
      LOGGER.error("Exception occurred PUT request processing ", e);
    }
  }
}
