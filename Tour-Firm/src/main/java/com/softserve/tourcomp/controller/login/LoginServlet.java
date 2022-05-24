package com.softserve.tourcomp.controller.login;

import com.softserve.tourcomp.constants.*;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;
import com.softserve.tourcomp.dao.impl.JDBCUserDao;
import com.softserve.tourcomp.dto.user.UserResponse;
import com.softserve.tourcomp.entity.Users;
import com.softserve.tourcomp.service.ServiceFactory;
import com.softserve.tourcomp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet({PathToPage.LOGIN_PATH})
public class LoginServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.getRequestDispatcher(PathToJsp.LOGIN_JSP).forward(req, resp);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    boolean result = false;
    Users ur = null;
    try {
      JDBCUserDao jdbcUserDao = new JDBCDaoFactory().createUserDao();
      ur = jdbcUserDao.validate(req.getParameter("email"), req.getParameter("password"));
      result = true;
    } catch (Exception e) {
      req.setAttribute("error", "Invalid login or password. Try again!");
      req.getRequestDispatcher(PathToJsp.LOGIN_JSP).forward(req, resp);
    }

    if (result) {
      req.getSession().setAttribute("user", ur);
      req.getSession().setAttribute("usid", ur.getId());
      req.getSession().setAttribute("session", "true");
      if (ur.getIsAdmin()) {
        req.getSession().setAttribute("isAdmin", "true");
      } else {
        req.getSession().setAttribute("isAdmin", "false");
      }
      req.getRequestDispatcher("index.jsp").include(req, resp);
    }
  }
}
