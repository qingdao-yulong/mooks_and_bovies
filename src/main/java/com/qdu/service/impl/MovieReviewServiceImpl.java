package com.qdu.service.impl;

import com.qdu.repository.MovieReviewRepository;
import com.qdu.entity.MovieReview;
import com.qdu.service.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    private final MovieReviewRepository mrd;

    @Autowired
    public MovieReviewServiceImpl(MovieReviewRepository mrd) {
        this.mrd = mrd;
    }

    @Override
    public MovieReview getMovieReviewById(int id) {
        return mrd.findById(id).get();
    }

    @Override
    public List<MovieReview> getMovieReviewsByUser(int user) {
        return mrd.getMovieReviewsByUser(user);
    }

    @Override
    public List<MovieReview> getMovieReviewsByMovie(int movie) {
        return mrd.getMovieReviewsByMovie(movie);
    }

    @Override
    public MovieReview createMovieReview(MovieReview movieReview) {
        return mrd.save(movieReview);
    }

    @Override
    public MovieReview updateMovieReview(MovieReview movieReview) {
        return mrd.save(movieReview);
    }

    @Override
    public void deleteMovieReview(int movieReview) {
        mrd.deleteById(movieReview);
    }
}
