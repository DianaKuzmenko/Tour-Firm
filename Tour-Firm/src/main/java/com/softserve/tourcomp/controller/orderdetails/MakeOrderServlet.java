package com.softserve.tourcomp.controller.orderdetails;

import com.softserve.tourcomp.constants.PathToJsp;
import com.softserve.tourcomp.dao.BookingDao;
import com.softserve.tourcomp.dao.HotelDao;
import com.softserve.tourcomp.dao.UserDao;
import com.softserve.tourcomp.dao.impl.JDBCDaoFactory;
import com.softserve.tourcomp.dao.impl.JDBCUserDao;
import com.softserve.tourcomp.entity.Bookings;
import com.softserve.tourcomp.entity.Hotels;
import com.softserve.tourcomp.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class MakeOrderServlet extends HttpServlet {
BookingDao bd=JDBCDaoFactory.getInstance().createBookingDao();
UserDao ud=JDBCDaoFactory.getInstance().createUserDao();
HotelDao hd=JDBCDaoFactory.getInstance().createHotelDao();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try{
      Bookings bork=new Bookings();

    bork.setUser(ud.findById((Long)req.getSession().getAttribute("usid")).get());
    bork.setStartDate((LocalDate) req.getSession().getAttribute("start"));
    bork.setEndDate((LocalDate)req.getSession().getAttribute("end"));
    Hotels hol=hd.findById(Long.parseLong(req.getParameter("hotelId"))).get();
    bork.setHotel(hol);
    bork.setAmountRooms(2);
    bork.setPrice(hol.getPriceNight());
    bd.create(bork);
    }
    catch (Exception e){
      req.setAttribute("error","System Error");
    }
    finally {
      resp.sendRedirect(req.getContextPath() + "/index");
    }
  }

}
