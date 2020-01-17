package com.travelagency_v2.travelagency_v2.models.bindingModels;

public class ReviewDto {

    private long id;

    //@Size(max = 300,message = "Review must be 300 characters long")
    private String content;

    //@Range(min = 1, max = 10,message = "Rate must be between 1 and 10")
    private int score;

    private String username;

    private long bookingProductId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getBookingProductId() {
        return bookingProductId;
    }

    public void setBookingProductId(long bookingProductId) {
        this.bookingProductId = bookingProductId;
    }
}
