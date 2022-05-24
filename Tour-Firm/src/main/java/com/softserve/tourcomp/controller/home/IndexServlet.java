package com.softserve.tourcomp.controller.home;

import com.softserve.tourcomp.constants.PathToJsp;
import com.softserve.tourcomp.constants.PathToPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(PathToPage.HOME_PATH)
public class IndexServlet extends HttpServlet {
  @Override
  public void init() throws ServletException {

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("session", req.getSession().getAttribute("session"));
    req.setAttribute("isAdmin", req.getSession().getAttribute("isAdmin"));
    req.getRequestDispatcher(PathToJsp.HOME_JSP).include(req, resp);
  }
}
