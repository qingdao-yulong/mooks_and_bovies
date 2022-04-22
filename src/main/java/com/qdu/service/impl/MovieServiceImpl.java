package com.qdu.service.impl;

import com.qdu.repository.MovieReviewRepository;
import com.qdu.repository.MovieRepository;
import com.qdu.entity.*;
import com.qdu.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository md;
    private MovieReviewRepository mrd;

    @Autowired
    public MovieServiceImpl(MovieRepository md, MovieReviewRepository mrd) {
        this.md = md;
        this.mrd = mrd;
    }

    @Override
    public Movie getMovieById(int id) {
        return md.findById(id).get();
    }

    @Override
    public Movie getMovieByEnglishName(String englishName) {
        return md.getMovieByEnglishName(englishName);
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return md.getMoviesByGenre(genre);
    }

    @Override
    public List<Movie> getMoviesByDirector(int director) {
        return md.getMoviesByDirector(director);
    }

    @Override
    public List<Movie> getMoviesByScreenwriter(int screenwriter) {
        return md.getMoviesByScreenwriter(screenwriter);
    }

    @Override
    public List<Movie> getMoviesByStarring(int actor) {
        return md.getMoviesByStarring(actor);
    }

    @Override
    public List<Movie> getMoviesByLanguage(String language) {
        return md.getMoviesByLanguage(language);
    }

    @Override
    public List<Movie> getMoviesByCountry(String country) {
        return md.getMoviesByCountry(country);
    }

    @Override
    public List<Movie> getAllMovies() {
        return md.findAll();
    }

    @Override
    public int getMovieRating(int movie) {
        List<MovieReview> reviewList = mrd.getMovieReviewsByMovie(movie);
        if (null != reviewList) {
            int sum = 0;
            for (MovieReview mr : reviewList) {
                sum += mr.getRating();
            }
            return sum / reviewList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Movie createMovie(Movie movie) {
        return md.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return md.save(movie);
    }

    @Override
    public void deleteMovie(int movie) {
        md.deleteById(movie);
    }
}
