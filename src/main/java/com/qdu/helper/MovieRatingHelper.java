package com.qdu.helper;

import com.qdu.entity.Movie;
import com.qdu.entity.MovieReview;
import com.qdu.service.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieRatingHelper {

    private MovieReviewService movieReviewService;

    @Autowired
    public MovieRatingHelper(MovieReviewService movieReviewService) {
        this.movieReviewService = movieReviewService;
    }

    public void getRatingsForList(List<Movie> movieList) {
        for (Movie m : movieList) {
            float sum = 0f;
            List<MovieReview> movieReviewList = movieReviewService.getMovieReviewsByMovie(m.getId());
            for (MovieReview mr : movieReviewList) {
                sum += mr.getRating();
            }
            sum = sum / movieReviewList.size();
            m.setRating(sum);
        }
    }

    public void getSingleRating(Movie movie) {
        float sum = 0f;
        List<MovieReview> movieReviewList = movieReviewService.getMovieReviewsByMovie(movie.getId());
        for (MovieReview mr : movieReviewList) {
            sum += mr.getRating();
        }
        sum = sum / movieReviewList.size();
        movie.setRating(sum);
    }
}
