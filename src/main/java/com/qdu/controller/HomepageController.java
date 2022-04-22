package com.qdu.controller;

import com.qdu.entity.*;
import com.qdu.helper.BookRatingHelper;
import com.qdu.helper.MovieRatingHelper;
import com.qdu.service.BookService;
import com.qdu.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class HomepageController {

    private BookService bookService;
    private MovieService movieService;
    private MovieRatingHelper movieRatingHelper;
    private BookRatingHelper bookRatingHelper;

    @Autowired
    public HomepageController(BookService bookService, MovieService movieService, MovieRatingHelper movieRatingHelper, BookRatingHelper bookRatingHelper) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.bookRatingHelper = bookRatingHelper;
        this.movieRatingHelper = movieRatingHelper;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Book> bookList = bookService.getAllBooks();
        int bls = bookList.size();
        if (bls > 5) {
            bookList = bookList.subList(0, 5);
        }
        bookRatingHelper.getRatingsForList(bookList);
        bookList.sort(Comparator.comparing(Book::getRating));
        model.addAttribute("books", bookList);

        List<Movie> movieList = movieService.getAllMovies();
        int mls = movieList.size();
        if (mls > 5) {
            movieList = movieList.subList(0, 5);
        }
        movieRatingHelper.getRatingsForList(movieList);
        movieList.sort(Comparator.comparing(Movie::getRating));
        model.addAttribute("movies", movieList);

        return "index";
    }

}
