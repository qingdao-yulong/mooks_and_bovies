package com.qdu.entity;

import javax.persistence.*;

@Entity
@Table(name = "Movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String englishName;
    private String genre;
    private int director;
    private int screenwriter;
    private int starring;
    private String language;
    private String releaseDate;
    private String country;
    private String description;
    private String image;
    private int duration;

    @Transient
    private float rating;

    public Movie(String englishName, String genre, int director, int screenwriter, int starring, String language, String releaseDate, String country, String description, String image, int duration) {
        this.englishName = englishName;
        this.genre = genre;
        this.director = director;
        this.screenwriter = screenwriter;
        this.starring = starring;
        this.language = language;
        this.releaseDate = releaseDate;
        this.country = country;
        this.description = description;
        this.image = image;
        this.duration = duration;
    }

    public Movie(int id, String englishName, String genre, int director, int screenwriter, int starring, String language, String releaseDate, String country, String description, int duration, float rating) {
        this.id = id;
        this.englishName = englishName;
        this.genre = genre;
        this.director = director;
        this.screenwriter = screenwriter;
        this.starring = starring;
        this.language = language;
        this.releaseDate = releaseDate;
        this.country = country;
        this.description = description;
        this.duration = duration;
        this.rating = rating;
    }

    public Movie() {

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    public int getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(int screenwriter) {
        this.screenwriter = screenwriter;
    }

    public int getStarring() {
        return starring;
    }

    public void setStarring(int starring) {
        this.starring = starring;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
