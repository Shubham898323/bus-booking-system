package com.redbus.Operator.util;


import com.itextpdf.io.exceptions.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import com.redbus.user.payload.BookingDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

import java.time.format.DateTimeFormatter;

@Service
public class PdfService {

    public byte[] generatePdf(BookingDto bookingDto) throws IOException, java.io.IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Creating a PdfDocument object
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            // Adding content to the PDF
            document.add(new Paragraph("Booking Details"));
            document.add(new Paragraph("Booking ID: " + bookingDto.getBookingId()));

            document.add(new Paragraph("Passenger Information:"));
            document.add(new Paragraph("Name: " + bookingDto.getFirstName() + " " + bookingDto.getLastName()));
            document.add(new Paragraph("Email: " + bookingDto.getEmail()));
            document.add(new Paragraph("Phone Number: " + bookingDto.getPhoneNumber()));

            document.add(new Paragraph("Bus Information:"));
            document.add(new Paragraph("Bus Number: " + bookingDto.getBusNumber()));
            document.add(new Paragraph("Bus Operator: " + bookingDto.getBusOperatorCompanyName()));
            document.add(new Paragraph("Departure City: " + bookingDto.getDepartureCity()));
            document.add(new Paragraph("Arrival City: " + bookingDto.getArrivalCity()));
            document.add(new Paragraph("Arrival Time: " + bookingDto.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm"))));
            document.add(new Paragraph("Departure Time: " + bookingDto.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm"))));
            document.add(new Paragraph("Departure Date: " + bookingDto.getDepartureDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            document.add(new Paragraph("Arrival Date: " + bookingDto.getArrivalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            document.add(new Paragraph("Total Travel Time: " + bookingDto.getTotalTravelTime() + " hours"));

            document.add(new Paragraph("Booking Cost:₹" + bookingDto.getCost()));

            document.add(new Paragraph("Booking Time: " + bookingDto.getBookingTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));

            // Close the Document
            document.close();
        }

        return baos.toByteArray();
    }
}