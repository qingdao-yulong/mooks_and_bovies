package com.qdu.controller;

import com.qdu.entity.MovieReview;
import com.qdu.entity.User;
import com.qdu.service.MovieReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Controller
@RequestMapping("movies/show/{movie}/movie_review")
public class MovieReviewController {

    private MovieReviewService movieReviewService;
    private MovieController movieController;

    @Autowired
    public MovieReviewController(MovieReviewService movieReviewService, MovieController movieController) {
        this.movieReviewService = movieReviewService;
        this.movieController = movieController;
    }

    @GetMapping("/create")
    public String toCreate(Model model, @PathVariable int movie) {
        model.addAttribute("movie", movie);
        return "movies/reviews/create";
    }

    @PostMapping("/create")
    public RedirectView create(Model model, @PathVariable int movie, @ModelAttribute MovieReview movieReview, @SessionAttribute User user) {
        String createdAt = LocalDate.now().toString();
        movieReview.setCreatedAt(createdAt);
        movieReview.setUser(user.getId());
        movieReview.setMovie(movie);
        movieReviewService.createMovieReview(movieReview);
        return new RedirectView("/movies/show/"+movie);
    }

    @GetMapping("/edit/{reviewId}")
    public String toEdit(Model model, @PathVariable int movie, @PathVariable int reviewId) {
            MovieReview review = movieReviewService.getMovieReviewById(reviewId);

            model.addAttribute("review", review);

            return "movies/reviews/edit";
    }

    @PostMapping("/edit/{reviewId}")
    public RedirectView edit(Model model, @PathVariable int movie, @PathVariable int reviewId, @ModelAttribute MovieReview movieReview) {
        movieReview.setId(reviewId);
        movieReviewService.updateMovieReview(movieReview);
        return new RedirectView("/movies/show/"+movie);
    }

    @GetMapping("/delete/{reviewId}")
    public RedirectView delete(Model model, @PathVariable int movie, @PathVariable int reviewId) {
        movieReviewService.deleteMovieReview(reviewId);
        return new RedirectView("/movies/show/"+movie);
    }
}
