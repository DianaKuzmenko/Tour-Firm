package com.softserve.tourcomp.controller.orderdetails;

import com.softserve.tourcomp.dao.BookingDao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookingsService extends HttpServlet {
  BookingDao bd = JDBCDaoFactory.getInstance().createBookingDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("bookin", bd.findBookingsByUserId((Long) req.getSession().getAttribute("usid")));
    req.getRequestDispatcher("bookings.jsp").include(req, resp);
  }
}
