package com.qdu.service;

import com.qdu.entity.MovieReview;

import java.util.List;

public interface MovieReviewService {

    public MovieReview getMovieReviewById(int id);

    public List<MovieReview> getMovieReviewsByUser(int user);

    public List<MovieReview> getMovieReviewsByMovie(int movie);

    public MovieReview createMovieReview(MovieReview movieReview);

    public MovieReview updateMovieReview(MovieReview movieReview);

    public void deleteMovieReview(int movieReview);

}
