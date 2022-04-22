package com.qdu.controller;

import com.qdu.entity.*;
import com.qdu.helper.ImageUploadHelper;
import com.qdu.helper.MovieRatingHelper;
import com.qdu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/movies")
public class MovieController {

    private MovieService movieService;
    private DirectorService directorService;
    private ActorService actorService;
    private ScreenwriterService screenwriterService;
    private MovieRatingHelper movieRatingHelper;
    private MovieReviewService movieReviewService;
    private UserService userService;
    private ImageUploadHelper imageUploadHelper;

    @Autowired
    public MovieController(MovieService movieService, DirectorService directorService, ActorService actorService, ScreenwriterService screenwriterService, MovieRatingHelper movieRatingHelper, MovieReviewService movieReviewService, UserService userService, ImageUploadHelper imageUploadHelper) {
        this.movieService = movieService;
        this.directorService = directorService;
        this.actorService = actorService;
        this.screenwriterService = screenwriterService;
        this.movieRatingHelper = movieRatingHelper;
        this.movieReviewService = movieReviewService;
        this.userService = userService;
        this.imageUploadHelper = imageUploadHelper;
    }

    @GetMapping("")
    public String index(Model model) {
        Map<Integer, Director> directorsMap = new HashMap<>();
        Map<Integer, Actor> actorsMap = new HashMap<>();
        Map<Integer, Screenwriter> screenwritersMap = new HashMap<>();
        List<Movie> movieList = movieService.getAllMovies();
//        movieRatingHelper.getRatingsForList(movieList);
        model.addAttribute("movies", movieList);
        // Add directors to model
        for (Movie m : movieList) {
            Director director = directorService.getDirectorByMovieId(m.getId());
            directorsMap.put(m.getId(), director);

            Actor actor =  actorService.getActorByMovieId(m.getId());
            actorsMap.put(m.getId(), actor);

            Screenwriter screenwriter = screenwriterService.getScreenwriterByMovieId(m.getId());
            screenwritersMap.put(m.getId(), screenwriter);
        }
        model.addAttribute("directors", directorsMap);
        // Add actors to model
        model.addAttribute("starring", actorsMap);
        // Add screenwriters to model
        model.addAttribute("screenwriters", screenwritersMap);

        return "movies/index";
    }

    @GetMapping("/create")
    public String toCreate(Model model) {
        List<Director> directors = directorService.getAllDirectors();
        List<Actor> actors = actorService.getAllActors();
        List<Screenwriter> screenwriters = screenwriterService.getAllScreenwriters();
        model.addAttribute("directors", directors);
        model.addAttribute("actors", actors);
        model.addAttribute("screenwriters", screenwriters);
        return "movies/create";
    }

    @PostMapping("/create")
    public RedirectView create(Model model, @ModelAttribute Movie movie, @RequestParam("fileImage") MultipartFile image) throws IOException {
        String fileName = imageUploadHelper.saveImage("movies/", image);
        movie.setImage(fileName);
        movieService.createMovie(movie);
        return new RedirectView("/movies", false);
    }

    @GetMapping("/show/{movie}")
    public String show(Model model, @PathVariable int movie, HttpServletRequest req) {
        int userId;
        // Get movie to show
        Movie movie1 = movieService.getMovieById(movie);
        model.addAttribute("movie", movie1);
        // Get related Director
        Director director = directorService.getDirectorById(movie1.getDirector());
        model.addAttribute("director", director);
        // Get related Actor
        Actor actor = actorService.getActorById(movie1.getStarring());
        model.addAttribute("starring", actor);
        // Get related Screenwriter
        Screenwriter screenwriter = screenwriterService.getScreenwriterById(movie1.getScreenwriter());
        model.addAttribute("screenwriter", screenwriter);

        List<MovieReview> movieReviews = movieReviewService.getMovieReviewsByMovie(movie);
        model.addAttribute("movieReviews", movieReviews);

        // Set rating against movie
        movieRatingHelper.getSingleRating(movie1);

        Map<Integer, String> map = new HashMap<>();
        for (MovieReview mr : movieReviews) {
            User u = userService.getUserById(mr.getUser());
            map.put(mr.getId(), u.getFirstName() + " " + u.getLastName());
        }
        model.addAttribute("users", map);

        HttpSession session = req.getSession();

        if (null != session.getAttribute("user")) {
            User user = (User) session.getAttribute("user");
            userId = user.getId();
        } else {
            userId = 0;
        }

        model.addAttribute("userId", userId);

        return "movies/movie_profile";
    }

    @GetMapping("/edit/{movieId}")
    public String toEdit(Model model, @PathVariable int movieId) {
        Movie movie = movieService.getMovieById(movieId);
        List<Director> directors = directorService.getAllDirectors();
        List<Actor> actors = actorService.getAllActors();
        List<Screenwriter> screenwriters = screenwriterService.getAllScreenwriters();
        model.addAttribute("movie", movie);
        model.addAttribute("directors", directors);
        model.addAttribute("actors", actors);
        model.addAttribute("screenwriters", screenwriters);
        return "/movies/edit";
    }

    @PostMapping("/edit/{movieId}")
    public RedirectView edit(Model model, @PathVariable int movieId, @ModelAttribute Movie movie, @RequestParam("fileImage") MultipartFile image) throws IOException {
        movie.setId(movieId);
        if (!image.getOriginalFilename().equals("")) {
            String fileName = imageUploadHelper.saveImage("movies/", image);
            movie.setImage(fileName);
        } else {
            Movie m = movieService.getMovieById(movieId);
            movie.setImage(m.getImage());
        }
        movieService.updateMovie(movie);

        return new RedirectView("/movies", false);
    }

    @GetMapping("/delete/{movie}")
    public String delete(@PathVariable int movie) {
        movieService.deleteMovie(movie);
        return "movies/index";
    }
}
