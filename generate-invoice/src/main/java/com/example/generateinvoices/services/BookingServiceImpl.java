package com.example.generateinvoices.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.generateinvoices.exceptions.CustomerSessionNotFoundException;
import com.example.generateinvoices.models.Booking;
import com.example.generateinvoices.models.CustomerSession;
import com.example.generateinvoices.models.CustomerSessionStatus;
import com.example.generateinvoices.models.Invoice;
import com.example.generateinvoices.models.Room;
import com.example.generateinvoices.repositories.BookingRepository;
import com.example.generateinvoices.repositories.CustomerSessionRepository;

public class BookingServiceImpl implements BookingService {

    private CustomerSessionRepository customerSessionRepository;
    private BookingRepository bookingRepository;

    public BookingServiceImpl(CustomerSessionRepository customerSessionRepository,
            BookingRepository bookingRepository) {
        this.customerSessionRepository = customerSessionRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Invoice generateInvoice(long userId) throws CustomerSessionNotFoundException {

        Optional<CustomerSession> customerSessionOptional = customerSessionRepository
                .findActiveCustomerSessionByUserId(userId);

        if (customerSessionOptional.isEmpty()) {
            throw new CustomerSessionNotFoundException("No customer session found");
        }

        List<Booking> bookings;
        double totalAmount = 0;
        double perBookingAmount = 0;
        Invoice invoice;
        
        Map<Room, Integer> bookedRooms = new HashMap<>();

        if (customerSessionOptional.get().isActive()) {
            bookings = bookingRepository.findBookingByCustomerSession(customerSessionOptional.get().getId());
            for (Booking booking : bookings) {
                for (Room room : booking.getBookedRooms().keySet()) {

                   

                    bookedRooms.put(room, booking.getBookedRooms().get(room) + bookedRooms.getOrDefault(room, 0));
                    perBookingAmount = (room.getPrice() * booking.getBookedRooms().get(room))+ perBookingAmount;
                }
                totalAmount = totalAmount + perBookingAmount;
            }
            invoice = calculateServiceTaxAndGst(totalAmount, 18, 10);
            invoice.setBookedRooms(bookedRooms);
            customerSessionOptional.get().setCustomerSessionStatus(CustomerSessionStatus.ENDED);
            customerSessionRepository.save(customerSessionOptional.get());

            return invoice;
        }

        return null;       
        
    }

    private Invoice calculateServiceTaxAndGst(double totalAmount, int gst, int serviceTax) {
        double gstAmount = totalAmount * gst / 100.0;
        double serviceTaxAmount = totalAmount * serviceTax / 100.0;
        double finalAmount = totalAmount + gstAmount + serviceTaxAmount;

        return new Invoice(null, finalAmount, gstAmount, serviceTaxAmount);
    }

}


/* 
package com.example.generateinvoices.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.generateinvoices.exceptions.CustomerSessionNotFoundException;
import com.example.generateinvoices.models.Booking;
import com.example.generateinvoices.models.CustomerSession;
import com.example.generateinvoices.models.CustomerSessionStatus;
import com.example.generateinvoices.models.Invoice;
import com.example.generateinvoices.models.Room;
import com.example.generateinvoices.repositories.BookingRepository;
import com.example.generateinvoices.repositories.CustomerSessionRepository;

public class BookingServiceImpl implements BookingService {

    private CustomerSessionRepository customerSessionRepository;
    private BookingRepository bookingRepository;

    public BookingServiceImpl(CustomerSessionRepository customerSessionRepository,
            BookingRepository bookingRepository) {
        this.customerSessionRepository = customerSessionRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Invoice generateInvoice(long userId) throws CustomerSessionNotFoundException {

        Optional<CustomerSession> customerSessionOptional = customerSessionRepository
                .findActiveCustomerSessionByUserId(userId);

        if (customerSessionOptional.isEmpty()) {
            throw new CustomerSessionNotFoundException("No customer session found");
        }

        List<Booking> bookings;
        double totalAmount = 0;
        double perBookingAmount = 0;
        Invoice invoice;
        
        Map<Room, Integer> bookedRooms = new HashMap<>();

        if (customerSessionOptional.get().isActive()) {
            bookings = bookingRepository.findBookingByCustomerSession(customerSessionOptional.get().getId());
            for (Booking booking : bookings) {
                for (Room room : booking.getBookedRooms().keySet()) {

                    bookedRooms.put(room, booking.getBookedRooms().get(room) + bookedRooms.getOrDefault(room, 0));
                    perBookingAmount = (room.getPrice() * booking.getBookedRooms().get(room))+ perBookingAmount;
                }
                totalAmount = totalAmount + perBookingAmount;
            }
            invoice = calculateServiceTaxAndGst(totalAmount, 18, 10);
            invoice.setBookedRooms(bookedRooms);
            customerSessionOptional.get().setCustomerSessionStatus(CustomerSessionStatus.ENDED);
            customerSessionRepository.save(customerSessionOptional.get());

            return invoice;
        }

        return null;       
        
    }

    private Invoice calculateServiceTaxAndGst(double totalAmount, int gst, int serviceTax) {
        double gstAmount = totalAmount * gst / 100.0;
        double serviceTaxAmount = totalAmount * serviceTax / 100.0;
        double finalAmount = totalAmount + gstAmount + serviceTaxAmount;

        return new Invoice(null, finalAmount, gstAmount, serviceTaxAmount);
    }

}
*/