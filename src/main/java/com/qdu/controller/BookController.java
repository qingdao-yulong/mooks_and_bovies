package com.qdu.controller;

import com.qdu.entity.*;
import com.qdu.helper.BookRatingHelper;
import com.qdu.helper.ImageUploadHelper;
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
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private BookReviewService bookReviewService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private UserService userService;
    private ImageUploadHelper imageUploadHelper;
    private BookRatingHelper bookRatingHelper;

    @Autowired
    public BookController(BookService bookService, BookReviewService bookReviewService, AuthorService authorService, PublisherService publisherService, UserService userService, ImageUploadHelper imageUploadHelper, BookRatingHelper bookRatingHelper) {
        this.bookService = bookService;
        this.bookReviewService = bookReviewService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.userService = userService;
        this.imageUploadHelper = imageUploadHelper;
        this.bookRatingHelper = bookRatingHelper;
    }

    @GetMapping("")
    public String index(Model model) {
        // get list of books
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("bookList", bookList);

        return "books/index";
    }

    @GetMapping("/create")
    public String toCreate(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
        return "books/create";
    }

    @PostMapping("/create")
    public RedirectView create(Model model, @ModelAttribute Book book, @RequestParam("fileImage") MultipartFile image) throws IOException {
        String fileName = imageUploadHelper.saveImage("books/", image);
        book.setImage(fileName);
        bookService.createBook(book);
        return new RedirectView("/books", false);
    }

    @GetMapping("/show/{book}")
    public String show(Model model, @PathVariable int book, HttpServletRequest req) {
        int userId;
        // Get book details
        Book b = bookService.getBookById(book);
        model.addAttribute("book", b);
        // Get related author
        Author author = authorService.getAuthorById(b.getAuthor());
        model.addAttribute("author", author);
        // Get related publisher
        Publisher publisher = publisherService.getPublisherById(b.getPublisher());
        model.addAttribute("publisher", publisher);
        // get list of book reviews
        List<BookReview> reviewList = bookReviewService.getBookReviewsByBook(book);
        model.addAttribute("reviewList", reviewList);

        bookRatingHelper.getSingleRating(b);

        Map<Integer, String> map = new HashMap<>();
        for (BookReview br : reviewList) {
            User u = userService.getUserById(br.getUserId());
            map.put(br.getId(), u.getFirstName() + " " + u.getLastName());
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

        return "/books/book_profile";
    }

    @GetMapping("/edit/{bookId}")
    public String toEdit(Model model, @PathVariable int bookId) {
        Book book = bookService.getBookById(bookId);
        List<Publisher> publishers = publisherService.getAllPublishers();
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("publishers", publishers);
        model.addAttribute("authors", authors);
        return "/books/edit";
    }

    @PostMapping("/edit/{bookId}")
    public RedirectView edit(Model model, @PathVariable int bookId, @ModelAttribute Book book, @RequestParam("fileImage") MultipartFile image) throws IOException {
        book.setId(bookId);
        if (!image.getOriginalFilename().equals("")) {
            String fileName = imageUploadHelper.saveImage("books/", image);
            book.setImage(fileName);
        } else {
            Book b = bookService.getBookById(bookId);
            book.setImage(b.getImage());
        }
        bookService.updateBook(book);

        return new RedirectView("/books", false);
    }

    @GetMapping("/delete/{book}")
    public void delete(Model model, @PathVariable int book) {
        bookService.deleteBook(book);
    }
}
