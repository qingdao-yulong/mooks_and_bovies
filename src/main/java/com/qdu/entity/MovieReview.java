package com.qdu.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "MovieReviews")
public class MovieReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "movie_id")
    private int movie;
    @Column(name = "user_id")
    private int user;
    private int rating;
    private String comments;
    private String createdAt;

    public MovieReview() {
    }

    public MovieReview(int movie, int user, int rating, String comments) {
        this.movie = movie;
        this.user = user;
        this.rating = rating;
        this.comments = comments;
        this.createdAt = LocalDate.now().toString();
    }

    public MovieReview(int id, int movie, int user, int rating, String comments) {
        this.id = id;
        this.movie = movie;
        this.user = user;
        this.rating = rating;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
