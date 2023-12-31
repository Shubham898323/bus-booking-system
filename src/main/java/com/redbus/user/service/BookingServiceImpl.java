package com.redbus.user.service;

import com.redbus.Operator.entity.BusOperator;
import com.redbus.Operator.entity.TicketCost;
import com.redbus.Operator.exception.ResourceNotFound;

import com.redbus.Operator.repository.BusOperatorRepository;
import com.redbus.Operator.repository.TicketCostRepository;
import com.redbus.user.entity.Booking;
import com.redbus.user.payload.BookingDto;
import com.redbus.user.payload.PassengerDetails;
import com.redbus.user.repository.BookingRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {


    @Value("${stripe.api.key}")
    private String stripeApiKey;


    private TicketCostRepository ticketCostRepository;
    private BusOperatorRepository busOperatorRepository;
    private BookingRepository bookingRepository;
    private ModelMapper modelMapper;

    // Constructor injection for repositories and ModelMapper


    public BookingServiceImpl(TicketCostRepository ticketCostRepository, BusOperatorRepository busOperatorRepository, BookingRepository bookingRepository, ModelMapper modelMapper) {

        this.ticketCostRepository = ticketCostRepository;
        this.busOperatorRepository = busOperatorRepository;
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    public BookingDto createBooking(
            String busId, String ticketId, PassengerDetails passengerDetails
    ) {
        // Find BusOperator by busId
        BusOperator bus = busOperatorRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFound("Bus not found with id: " + busId));

        // Find TicketCost by ticketId
        TicketCost ticket = ticketCostRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFound("Ticket Cost not found with id: " + ticketId));


        String paymentIntent = createPaymentIntent((int) ticket.getCost());
        if(paymentIntent!=null) {

            // Map DTO to Entity
            Booking booking = modelMapper.map(passengerDetails, Booking.class);
            // Set other fields as needed
            booking.setBookingId(UUID.randomUUID().toString());
            booking.setBusId(busId);
            booking.setTicketId(ticketId);
            booking.setBusNumber(bus.getBusNumber());
            booking.setBusOperatorCompanyName(bus.getBusOperatorCompanyName());
            booking.setDepartureCity(bus.getDepartureCity());  // Assuming you have this information in PassengerDetails
            booking.setArrivalCity(bus.getArrivalCity());  // Assuming you have this information in PassengerDetails
            booking.setArrivalTime(bus.getArrivalTime());  // Assuming you have this information in BusOperator
            booking.setDepartureTime(bus.getDepartureTime());  // Assuming you have this information in BusOperator
            booking.setDepartureDate(bus.getDepartureDate());  // Assuming you have this information in PassengerDetails
            booking.setArrivalDate(bus.getArrivalDate());  // Assuming you have this information in PassengerDetails
            booking.setTotalTravelTime(bus.getTotalTravelTime());
            booking.setCost(ticket.getCost());
            booking.setBookingTime(LocalDateTime.now());
            // Save the Booking entity
            Booking savedBooking = bookingRepository.save(booking);
            // Map the saved Booking entity to BookingDto
            BookingDto bookingDto = modelMapper.map(savedBooking, BookingDto.class);
            bookingDto.setMessage("Booking Confirmed");
            // Return the BookingDto in the response
            return bookingDto;

        }else {
            System.out.println("Error!!!");
        }
       return null;


    }



    public String createPaymentIntent( Integer amount) {
        Stripe.apiKey = stripeApiKey;
        try {
            PaymentIntent intent = PaymentIntent.create(
                    new PaymentIntentCreateParams.Builder()
                            .setCurrency("usd")
                            .setAmount((long) amount * 100)
                            .build()
            );
            return generateResponse(intent.getClientSecret());
        } catch (StripeException e) {
            // Handle StripeException
            return generateResponse("Error creating PaymentIntent:" + e.getMessage());
        }
    }

    private String generateResponse(String clientSecret) {
        // You can customize this method to generate the desired response.
        return "{\"clientSecret\":\"" + clientSecret + "\"}";
    }

}




