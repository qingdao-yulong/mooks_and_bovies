package com.qdu.entity;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String englishName;
    private float price;
    private String description;
    private String genre;
    private String releaseDate;
    private int author;
    private int publisher;
    private int pages;
    private String country;
    private String language;
    private String image;

    @Transient
    private float rating;

    public Book() {
    }

    public Book(int id, String englishName, float price, String description, String genre, String releaseDate, int author, int publisher, int pages, String country, String language, String image) {
        this.id = id;
        this.englishName = englishName;
        this.price = price;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.country = country;
        this.language = language;
        this.image = image;
    }

    public Book(String englishName, float price, String description, String genre, String releaseDate, int author, int publisher, int pages, String country, String language, String image) {
        this.englishName = englishName;
        this.price = price;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.country = country;
        this.language = language;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
