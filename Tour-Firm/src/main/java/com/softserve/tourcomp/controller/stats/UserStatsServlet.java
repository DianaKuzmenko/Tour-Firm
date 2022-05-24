package com.softserve.tourcomp.controller.stats;

import com.softserve.tourcomp.service.ServiceFactory;
import com.softserve.tourcomp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserStatsServlet extends HttpServlet {
  private UserService us= ServiceFactory.getInstance().getUserService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setAttribute("userstats",us.userStats());
    req.getRequestDispatcher("userstats.jsp").include(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
