package com.softserve.tourcomp.service;

public class ServiceFactory {
  private BookingService bookingService;
  private CityService cityService;
  private CountryService countryService;
  private HotelService hotelService;
  private UserService userService;
  private VisaService visaService;

  private static ServiceFactory instance;

  private ServiceFactory() {

  }

  public static ServiceFactory getInstance() {
    if (instance == null) {
      instance = new ServiceFactory();
    }
    return instance;
  }

  public BookingService getBookingService() {
    if (bookingService == null) {
      synchronized (BookingService.class) {
        if (bookingService == null) {
          bookingService = new BookingService();
        }
      }
    }
    return bookingService;
  }

  public CityService getCityService() {
    if (cityService == null) {
      synchronized (CityService.class) {
        if (cityService == null) {
          cityService = new CityService();
        }
      }
    }
    return cityService;
  }

  public CountryService getCountryService() {
    if (countryService == null) {
      synchronized (CountryService.class) {
        if (countryService == null) {
          countryService = new CountryService();
        }
      }
    }
    return countryService;
  }

  public HotelService getHotelService() {
    if (hotelService == null) {
      synchronized (HotelService.class) {
        if (hotelService == null) {
          hotelService = new HotelService();
        }
      }
    }
    return hotelService;
  }

  public UserService getUserService() {
    if (userService == null) {
      synchronized (UserService.class) {
        if (userService == null) {
          userService = new UserService();
        }
      }
    }
    return userService;
  }

  public VisaService getVisaService() {
    if (visaService == null) {
      synchronized (VisaService.class) {
        if (visaService == null) {
          visaService = new VisaService();
        }
      }
    }
    return visaService;
  }

}
