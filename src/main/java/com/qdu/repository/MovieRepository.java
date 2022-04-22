package com.qdu.repository;

import com.qdu.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public Movie getMovieByEnglishName(String englishName);

    public List<Movie> getMoviesByGenre(String genre);

    public List<Movie> getMoviesByDirector(int directorId);

    public List<Movie> getMoviesByScreenwriter(int screenwriter);

    public List<Movie> getMoviesByStarring(int actor);

    public List<Movie> getMoviesByLanguage(String language);

    public List<Movie> getMoviesByCountry(String country);

}
