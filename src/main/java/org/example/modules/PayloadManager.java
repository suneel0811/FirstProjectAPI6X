package org.example.modules;

import com.google.gson.Gson;
import org.example.pojos.*;
import org.example.utills.PropertyFileDataReader;

public class PayloadManager {
       Gson gson =new Gson();
       PropertyFileDataReader propertyFileDataReader = new PropertyFileDataReader();

    public String createPayloadBookingAsString(){
        Booking booking=new Booking();
        booking.setFirstname("suneel");
        booking.setLastname("kumar");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bd=new BookingDates();
        bd.setCheckin("2020-01-01");
        bd.setCheckout("2024-01-03");

        booking.setBookingDates(bd);
        booking.setAdditionalneeds("Breakfast");
        booking.setAdditionalneeds(propertyFileDataReader.readData("booking.additionalNeeds"));
    return gson.toJson(booking);
    }


    public String fullUpdatePayloadBookingAsString(){
        Booking booking=new Booking();
        booking.setFirstname("kumar");
        booking.setLastname("reddy");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bd=new BookingDates();
        bd.setCheckin("2020-01-01");
        bd.setCheckout("2024-01-03");

        booking.setBookingDates(bd);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);
    }

    public BookingResponse bookingResponseJava(String responseString){
        BookingResponse bookingResponse=gson.fromJson(responseString,BookingResponse.class);
        System.out.println(bookingResponse);
        return bookingResponse;
    }

    public String setAuthPayload(){
        Auth auth=new Auth();
        auth.setUsername(propertyFileDataReader.readData("username"));
        auth.setPassword(propertyFileDataReader.readData("password"));
        gson=new Gson();
        String jsonPayloadString=gson.toJson(auth);
        System.out.println("Payload set to "+jsonPayloadString);
        return jsonPayloadString;
    }

   public String getTokenFromJSON(String tokenResponse){
        gson =new Gson();

        //Response(JSON format) --> Object TokenResponse
        //Deserialization

        TokenResponse tokenResponse1=gson.fromJson(tokenResponse, TokenResponse.class);
         return tokenResponse1.getToken();
    }

    public Booking getResponseFromJson(String getResponse){
        gson =new Gson();
        //Response(JSON format) --> Object TokenResponse
        //Deserialization
        Booking booking=gson.fromJson(getResponse, Booking.class);
        System.out.println(booking);
        return booking;
    }

}
