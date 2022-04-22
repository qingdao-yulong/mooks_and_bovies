package com.qdu.repository;

import com.qdu.entity.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Integer> {

    public List<MovieReview> getMovieReviewsByUser(int user);

    public List<MovieReview> getMovieReviewsByMovie(int movie);

}
