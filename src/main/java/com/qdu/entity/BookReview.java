package com.qdu.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BookReviews")
public class BookReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int bookId;
    private int userId;
    private int rating;
    private String comments;
    private String createdAt;

    public BookReview() {
    }

    public BookReview(int bookId, int userId, int rating, String comments) {
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.comments = comments;
        this.createdAt = LocalDateTime.now().toString();
    }

    public BookReview(int id, int bookId, int userId, int rating, String comments) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.comments = comments;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook() {
        return bookId;
    }

    public void setBook(int bookId) {
        this.bookId = bookId;
    }

    public int getInt() {
        return userId;
    }

    public void setInt(int userId) {
        this.userId = userId;
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
