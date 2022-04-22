package com.qdu.controller;

import com.qdu.entity.BookReview;
import com.qdu.entity.MovieReview;
import com.qdu.entity.User;
import com.qdu.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Controller
@RequestMapping("books/show/{book}/book_review")
public class BookReviewController {

    private BookReviewService bookReviewService;

    @Autowired
    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    @GetMapping("/create")
    public String toCreate(Model model, @PathVariable int book) {
        model.addAttribute("book", book);
        return "books/reviews/create_book_review";
    }

    @PostMapping("/create")
    public RedirectView create(Model model, @PathVariable int book, @ModelAttribute BookReview bookReview, @SessionAttribute User user) {
        String createdAt = LocalDate.now().toString();
        bookReview.setCreatedAt(createdAt);
        bookReview.setBook(book);
        bookReview.setUserId(user.getId());
        bookReviewService.createBookReview(bookReview);
        return new RedirectView("/books/show/"+book, false);
    }

    @GetMapping("/edit/{reviewId}")
    public String toEdit(Model model, @PathVariable int book, @PathVariable int reviewId) {
        BookReview review = bookReviewService.getBookReviewById(reviewId);

        model.addAttribute("review", review);

        return "books/reviews/edit_book_review";
    }

    @PostMapping("/edit/{review}")
    public RedirectView edit(Model model, @PathVariable int book, @PathVariable int review, @ModelAttribute BookReview bookReview) {
        bookReview.setId(review);
        bookReviewService.updateBookReview(bookReview);
        return new RedirectView("books/show/"+book, false);
    }

    @GetMapping("/delete/{review}")
    public RedirectView delete(Model model, @PathVariable int book, @PathVariable int review) {
        bookReviewService.deleteBookReview(review);
        return new RedirectView("/books/show/"+book);
    }
}
