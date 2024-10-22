package com.example.ryokouikitai.Trip.DTO;


import com.example.ryokouikitai.domain.area.Attraction;

public class CourseAttractionDTO {
    private Integer day;
    private Integer orders;
    private Attraction attraction;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }
}
