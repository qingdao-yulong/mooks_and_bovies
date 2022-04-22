package com.qdu.service;

import com.qdu.entity.Movie;

import java.util.List;

public interface MovieService {

    public Movie getMovieById(int id);

    public Movie getMovieByEnglishName(String englishName);

    public List<Movie> getMoviesByGenre(String genre);

    public List<Movie> getMoviesByDirector(int director);

    public List<Movie> getMoviesByScreenwriter(int screenwriter);

    public List<Movie> getMoviesByStarring(int actor);

    public List<Movie> getMoviesByLanguage(String language);

    public List<Movie> getMoviesByCountry(String country);

    public List<Movie> getAllMovies();

    public int getMovieRating(int movie);

    public Movie createMovie(Movie movie);

    public Movie updateMovie(Movie movie);

    public void deleteMovie(int movie);

}
