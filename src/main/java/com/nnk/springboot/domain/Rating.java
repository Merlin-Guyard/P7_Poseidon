package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="moodysRating")
    String moodysRating;

    @Column(name="sandPRating")
    String sandPRating;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name="fitchRating")
    String fitchRating;

    @Positive(message = "Enter a positive number above 0")
    @Column(name="orderNumber")
    int orderNumber;

    public Rating() {
    }

    public Rating(String moodysRating, String fitchRating, int orderNumber) {
        this.moodysRating = moodysRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Rating(String moodysRating, String sandPRating, String fitchRating, int i) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
