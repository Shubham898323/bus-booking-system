package com.redbus.user.controller;

import com.redbus.Operator.util.EmailService;
import com.redbus.Operator.util.PdfService;
import com.redbus.user.payload.BookingDto;
import com.redbus.user.payload.PassengerDetails;
import com.redbus.user.service.BookingService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private EmailService emailService;
    private BookingService bookingService;
    private PdfService pdfService;

    public BookingController(BookingService bookingService, EmailService emailService, PdfService pdfService) {
        this.bookingService = bookingService;
        this.emailService = emailService;
        this.pdfService = pdfService;
    }

    @PostMapping
    public ResponseEntity<BookingDto> bookBus(
            @RequestParam("busId") String busId,
            @RequestParam("ticketId") String ticketId,
            @RequestBody PassengerDetails passengerDetails
    ) throws IOException, MessagingException {
        // Call the service layer to create a booking
        BookingDto booking = bookingService.createBooking(busId, ticketId, passengerDetails);

        if (booking != null) {
            // Generate PDF
            byte[] pdfAttachment = pdfService.generatePdf(booking);

            // Send email with PDF attachment
            emailService.sendEmailWithAttachment(
                    passengerDetails.getEmail(),
                    "Booking Confirmed. Booking Id: " + booking.getBookingId(),
                    "Your Booking is Confirmed. Passenger: " + passengerDetails.getFirstName() + " " + passengerDetails.getLastName(),
                    pdfAttachment,
                    "BookingConfirmation"
            );

            return new ResponseEntity<>(booking, HttpStatus.OK);
        }
        return null;
    }

}