package com.softserve.tourcomp.controller.registration;

import com.softserve.tourcomp.constants.PathToJsp;
import com.softserve.tourcomp.dao.CountryDao;
import com.softserve.tourcomp.dao.UserDao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;
import com.softserve.tourcomp.entity.Users;
import com.softserve.tourcomp.service.CountryService;
import com.softserve.tourcomp.service.ServiceFactory;
import com.softserve.tourcomp.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
  private UserService us;
  private CountryService cs;
  private CountryDao jc;
  private UserDao ud;

  @Override
  public void init() throws ServletException {
    us = ServiceFactory.getInstance().getUserService();
    jc = JDBCDaoFactory.getInstance().createCountryDao();
    ud = JDBCDaoFactory.getInstance().createUserDao();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Users user = new Users();
    try {
      user.setId(228L);
      user.setEmail(request.getParameter("email"));
      user.setPassword(request.getParameter("password"));
      user.setFirstName(request.getParameter("firstName"));
      user.setLastName(request.getParameter("lastName"));
      user.setCountry(jc.findByCountryName(request.getParameter("country")).get());
      user.setIsAdmin(false);
      if (ud.create(user)) {
        response.sendRedirect(request.getContextPath() + "/login");
      } else {
        throw new Exception();
      }
    } catch (Exception exp) {
      request.setAttribute("error", "Email is already used.\nPlease do not leave empty fields!");
      request.getRequestDispatcher(PathToJsp.REGISTRATION_JSP).forward(request, response);
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(PathToJsp.REGISTRATION_JSP).forward(request, response);
  }
}
