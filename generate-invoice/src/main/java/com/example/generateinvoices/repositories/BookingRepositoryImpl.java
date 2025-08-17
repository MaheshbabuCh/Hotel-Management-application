package com.example.generateinvoices.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.generateinvoices.models.Booking;

public class BookingRepositoryImpl implements BookingRepository {

    Map<Long, List<Booking>> bookingsMap = new HashMap<>();

    @Override
    public Booking save(Booking booking) {
        bookingsMap.put(booking.getCustomerSession().getId(), List.of(booking));
        return booking;
    }

    @Override
    public List<Booking> findBookingByCustomerSession(long customerSessionId) {
        return bookingsMap.get(customerSessionId);
    }
    
}
