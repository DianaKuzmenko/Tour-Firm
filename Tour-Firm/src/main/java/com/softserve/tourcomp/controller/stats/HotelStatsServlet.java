package com.softserve.tourcomp.controller.stats;

import com.softserve.tourcomp.dao.CountryDao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;
import com.softserve.tourcomp.service.HotelService;
import com.softserve.tourcomp.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HotelStatsServlet extends HttpServlet {
  HotelService hs= ServiceFactory.getInstance().getHotelService();
  @Override
  public void init() throws ServletException {

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      req.setAttribute("hotelstats",hs.statistic());
    } catch (SQLException e) {
      req.setAttribute("error","Can't find objects");
    }
    req.getRequestDispatcher("hotelstats.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
