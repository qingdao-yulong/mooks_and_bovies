package com.qdu.controller;

import com.qdu.entity.Book;
import com.qdu.entity.Publisher;
import com.qdu.service.BookService;
import com.qdu.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherService publisherService;
    private BookService bookService;

    @Autowired
    public PublisherController(PublisherService publisherService, BookService bookService) {
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Publisher> list = publisherService.getAllPublishers();
        model.addAttribute("publishers", list);
        return "publishers/index";
    }

    @GetMapping("/create")
    public String toCreate(Model model) {
        return "publishers/create";
    }

    @PostMapping("/create")
    public RedirectView create(Model model, @ModelAttribute Publisher publisher) {
        publisherService.createPublisher(publisher);
        return new RedirectView("/publishers", false);
    }

    @GetMapping("/edit/{publisherId}")
    public String toEdit(Model model, @PathVariable int publisherId) {
        Publisher publisher = publisherService.getPublisherById(publisherId);
        model.addAttribute("publisher", publisher);
        return "publishers/edit";
    }

    @PostMapping("/edit/{publisherId}")
    public RedirectView edit(Model model, @PathVariable int publisherId, @ModelAttribute Publisher publisher) {
        publisher.setId(publisherId);
        publisherService.updatePublisher(publisher);
        return new RedirectView("/publishers", false);
    }

    @GetMapping("/show/{publisherId}")
    public String show(Model model, @PathVariable int publisherId) {
        Publisher publisher = publisherService.getPublisherById(publisherId);
        List<Book> books = bookService.getBooksByPublisher(publisherId);

        model.addAttribute("publisher", publisher);
        model.addAttribute("books", books);

        return "publishers/publisher_profile";
    }

    @GetMapping("/delete/{publisherId}")
    @ResponseBody
    public void delete(Model model, @PathVariable int publisherId) {
        publisherService.deletePublisher(publisherId);
    }

}
