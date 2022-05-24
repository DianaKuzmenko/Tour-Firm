package com.softserve.tourcomp.constants;

public class Attribute {

  public final static String ERROR = "error";

  //login
  public static final String EMAIL = "email";
  public static final String PASSWORD = "password";

  // Country
  public static final String COUNTRY_NAME = "countryName";
  public final static String COUNTRY_ID = "idCountry";

  //City
  public static final String CITY_ID = "idCity";
  public static final String CITY_NAME = "cityName";
  public static final String COUNTRY = "Country_idCountry";


  //Hotel
  public static final String HOTEL_NAME = "hotelName";
  public static final String HOTEL_ID = "idHotel";
  public static final String CITY = "City_idCity";
  public static final String AVAILABLE_COUNT = "availableCount";


  //OrderDetails
  public static final String ORDER_ID = "idOrder";
  public static final String BEGIN_DATE = "beginDate";
  public static final String END_DATE = "endDate";
  public static final String CLIENT = "Client_idClient";
  public static final String HOTEL = "Hotel_idHotel";


  //Client
  public static final String CLIENT_ID = "clientId";
  public static final String CLIENT_NAME = "clientName";
  public static final String CLIENT_SURNAME = "clientSurname";
  public static final String CLIENT_PHONENUMBER = "clientPhoneNumber";

}
