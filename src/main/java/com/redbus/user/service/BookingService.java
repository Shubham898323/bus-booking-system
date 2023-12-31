package com.redbus.user.service;

import com.redbus.user.payload.BookingDto;
import com.redbus.user.payload.PassengerDetails;

public interface BookingService {


    BookingDto createBooking(String busId, String ticketId, PassengerDetails passengerDetails);
}
