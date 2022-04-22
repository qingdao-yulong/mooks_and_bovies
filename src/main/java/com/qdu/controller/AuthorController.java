package com.qdu.controller;

import com.qdu.entity.Author;
import com.qdu.entity.Book;
import com.qdu.entity.Movie;
import com.qdu.helper.ImageUploadHelper;
import com.qdu.service.AuthorService;
import com.qdu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private ImageUploadHelper imageUploadHelper;
    private BookService bookService;

    @Autowired
    public AuthorController(AuthorService authorService, ImageUploadHelper imageUploadHelper, BookService bookService) {
        this.authorService = authorService;
        this.imageUploadHelper = imageUploadHelper;
        this.bookService = bookService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors/index";
    }

    @GetMapping("/create")
    public String toCreate(Model model) {
        return "authors/create";
    }

    @PostMapping("/create")
    public RedirectView create(Model model, @ModelAttribute Author author, @RequestParam("fileImage") MultipartFile image) throws IOException {
        String fileName = imageUploadHelper.saveImage("authors/", image);
        author.setImage(fileName);
        authorService.createAuthor(author);
        return new RedirectView("/authors", false);
    }

    @GetMapping("/edit/{authorId}")
    public String toEdit(Model model, @PathVariable int authorId) {
        Author a = authorService.getAuthorById(authorId);
        model.addAttribute("author", a);
        return "authors/edit";
    }

    @PostMapping("/edit/{authorId}")
    public RedirectView edit(Model model, @PathVariable int authorId, @ModelAttribute Author author, @RequestParam("fileImage") MultipartFile image) throws IOException {
        author.setId(authorId);
        // Check if image field is populated, if not, use the previous image
        if (!image.getOriginalFilename().equals("")) {
            String fileName = imageUploadHelper.saveImage("authors/", image);
            author.setImage(fileName);
        } else {
            Author a = authorService.getAuthorById(authorId);
            author.setImage(a.getImage());
        }
        authorService.editAuthor(author);
        return new RedirectView("/authors", false);
    }

    @GetMapping("/show/{authorId}")
    public String show(Model model, @PathVariable int authorId) {
        Author author = authorService.getAuthorById(authorId);
        List<Book> books = bookService.getBooksByAuthor(authorId);

        model.addAttribute("author", author);
        model.addAttribute("books", books);

        return "authors/author_profile";
    }

    @GetMapping("/delete/{authorId}")
    @ResponseBody
    public void delete(Model model, @PathVariable int authorId) {
        authorService.deleteAuthor(authorId);
    }
}
