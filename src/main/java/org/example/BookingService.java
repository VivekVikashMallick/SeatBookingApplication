package org.example;

import java.util.ArrayList;

public class BookingService {
    private final Event ev;
    private final ArrayList<Integer> availableSeats;
    private final ArrayList<Booking> bookings;

    public BookingService(Event ev){
        this.ev = ev;
        this.bookings = new ArrayList<>();
        this.availableSeats = new ArrayList<>();

        for (int seat =1;seat<= ev.getTotalSeats();seat++){
            availableSeats.add(seat);
        }

    }
    public synchronized void bookSeat(String customerName,int seatNumber)
            throws InvalidBookingException{
        validateSeat(seatNumber);
        if (!availableSeats.contains(seatNumber)){
            throw new InvalidBookingException("Seat" + seatNumber + "is already booked.");
        }
        availableSeats.remove(Integer.valueOf(seatNumber));

        Booking booking =new Booking(customerName, seatNumber);
        bookings.add(booking);
        System.out.println("Success : " + booking);
    }
    public synchronized ArrayList<Integer> getAvailableSeats() {
        return new ArrayList<>(availableSeats);
    }
    public synchronized ArrayList<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
    public void validateSeat(int seatNumber)throws InvalidBookingException{
        if (seatNumber < 1 || seatNumber > ev.getTotalSeats()) {
            throw new InvalidBookingException(
                    "Seat " + seatNumber
                            + " is invalid. Valid seats are 1 to "
                            + ev.getTotalSeats() + ".");
        }
    }
    public synchronized void displaySummary(){
        System.out.println("\n------Booking Summary----");
        System.out.println("Event :"+ ev.getTitle());
        System.out.println("Available seats :"+ availableSeats);
        System.out.println("Successful booking : "+ bookings);
        System.out.println("Total bookings : "+ bookings.size());


    }

}
