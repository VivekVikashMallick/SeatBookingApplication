package org.example;

public class Event {
    private final String title;
    private final int totalSeats;

    public Event(String title, int totalSeat) {
        this.title = title;
        this.totalSeats = totalSeat;
    }

    public String getTitle() {
        return title;
    }

    public int getTotalSeats() {
        return totalSeats;
    }
}
