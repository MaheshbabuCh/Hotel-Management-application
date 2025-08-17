package com.example.generateinvoices.controllers;

import com.example.generateinvoices.dtos.GenerateInvoiceRequestDto;
import com.example.generateinvoices.dtos.GenerateInvoiceResponseDto;
import com.example.generateinvoices.dtos.ResponseStatus;
import com.example.generateinvoices.exceptions.CustomerSessionNotFoundException;
import com.example.generateinvoices.models.Invoice;
import com.example.generateinvoices.services.BookingService;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public GenerateInvoiceResponseDto generateInvoice(GenerateInvoiceRequestDto requestDto) {

        Invoice invoice;
        GenerateInvoiceResponseDto response = new GenerateInvoiceResponseDto();

        try {
           
            invoice =   bookingService.generateInvoice(requestDto.getUserId());
            if(invoice == null){
                response.setResponseStatus(ResponseStatus.FAILURE);
                return response;
            }
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setInvoice(invoice);

        } catch (CustomerSessionNotFoundException e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        
        return response;
    }
}
