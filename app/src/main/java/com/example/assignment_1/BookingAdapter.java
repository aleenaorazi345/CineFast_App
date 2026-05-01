package com.example.assignment_1;

import java.util.Map;

public class Booking {
    private String userId;
    private String userEmail;
    private String bookingId;
    private String movieName;
    private int seats;
    private int totalPrice;
    private String dateTime;
    private long timestamp;
    private Map<String, Integer> snacksDetails;

    // Required empty constructor for Firebase
    public Booking() {}

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public Map<String, Integer> getSnacksDetails() { return snacksDetails; }
    public void setSnacksDetails(Map<String, Integer> snacksDetails) { this.snacksDetails = snacksDetails; }
}