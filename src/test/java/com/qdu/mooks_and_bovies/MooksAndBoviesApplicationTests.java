package com.qdu.mooks_and_bovies;

import com.qdu.entity.Director;
import com.qdu.entity.Movie;
import com.qdu.entity.User;
import com.qdu.service.DirectorService;
import com.qdu.service.MovieReviewService;
import com.qdu.service.MovieService;
import com.qdu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MooksAndBoviesApplicationTests {

    private UserService userService;
    private DirectorService directorService;
    private MovieReviewService movieReviewService;
    private MovieService movieService;

    @Autowired
    MooksAndBoviesApplicationTests(UserService userService, DirectorService directorService, MovieReviewService movieReviewService, MovieService movieService) {
        this.userService = userService;
        this.directorService = directorService;
        this.movieReviewService = movieReviewService;
        this.movieService = movieService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testDirectorCrud() {
        Director director = new Director(
                "username",
                "firstname",
                "25",
                "testimage",
                "England"
        );

        Director d = directorService.createDirector(director);
    }

    @Test
    void test2() {
        List<Movie> movies = movieService.getMoviesByStarring(3);
        System.out.println(movies);
    }

}
