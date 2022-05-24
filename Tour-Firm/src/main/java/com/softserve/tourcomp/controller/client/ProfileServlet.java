package com.softserve.tourcomp.controller.client;

import com.softserve.tourcomp.dao.UserDao;
import com.softserve.tourcomp.dao.VisaDao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;
import com.softserve.tourcomp.dto.visa.VisaRequest;
import com.softserve.tourcomp.entity.Users;
import com.softserve.tourcomp.entity.Visas;
import com.softserve.tourcomp.service.ServiceFactory;
import com.softserve.tourcomp.service.UserService;
import com.softserve.tourcomp.service.VisaService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProfileServlet extends HttpServlet {
  VisaService vs = ServiceFactory.getInstance().getVisaService();
  UserService us = ServiceFactory.getInstance().getUserService();
  UserDao ud = JDBCDaoFactory.getInstance().createUserDao();
  VisaDao vd = JDBCDaoFactory.getInstance().createVisaDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      List<Visas> lv = ud.getVisas((Long) req.getSession().getAttribute("usid"));
      if (lv.isEmpty()) {
        throw new Exception();
      } else {
        req.setAttribute("visas", lv);
      }
    } catch (Exception e) {
      req.setAttribute("visas", "null");
    } finally {
      req.setAttribute("user", req.getSession().getAttribute("user"));
    }

    req.getRequestDispatcher("userPage.jsp").include(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Visas visar = vd.findByName(req.getParameter("inputVisa")).get();
    Users user = ud.findById((Long) req.getSession().getAttribute("usid")).get();
    user.getVisas().add(visar);
    if (req.getParameter("Name") != null) {
      user.setFirstName(req.getParameter("Name"));
    }
    if (req.getParameter("LastName") != null) {
      user.setLastName(req.getParameter("LastName"));
    }
    ud.update(user);
    req.getSession().setAttribute("user", user);
    req.setAttribute("user", req.getSession().getAttribute("user"));
    resp.sendRedirect(req.getContextPath() + "/profile");
  }
}

