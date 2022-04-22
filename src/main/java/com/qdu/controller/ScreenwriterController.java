package com.qdu.controller;

import com.qdu.entity.Movie;
import com.qdu.entity.Screenwriter;
import com.qdu.helper.ImageUploadHelper;
import com.qdu.service.MovieService;
import com.qdu.service.ScreenwriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/screenwriters")
public class ScreenwriterController {

    private ScreenwriterService screenwriterService;
    private ImageUploadHelper imageUploadHelper;
    private MovieService movieService;

    @Autowired
    public ScreenwriterController(ScreenwriterService screenwriterService, ImageUploadHelper imageUploadHelper, MovieService movieService) {
        this.screenwriterService = screenwriterService;
        this.imageUploadHelper = imageUploadHelper;
        this.movieService = movieService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Screenwriter> list = screenwriterService.getAllScreenwriters();
        model.addAttribute("screenwriters", list);
        return "screenwriters/index";
    }

    @GetMapping("/create")
    public String toCreate(Model model) {
        return "screenwriters/create";
    }

    @PostMapping("/create")
    public RedirectView create(Model model, @ModelAttribute Screenwriter screenwriter, @RequestParam("fileImage") MultipartFile image) throws IOException {
        String fileName = imageUploadHelper.saveImage("screenwriters", image);
        screenwriter.setImage(fileName);
        screenwriterService.createScreenwriter(screenwriter);
        return new RedirectView("/screenwriters", false);
    }

    @GetMapping("/edit/{screenwriterId}")
    public String toEdit(Model model, @PathVariable int screenwriterId) {
        Screenwriter screenwriter = screenwriterService.getScreenwriterById(screenwriterId);
        model.addAttribute("screenwriter", screenwriter);
        return "screenwriters/edit";
    };

    @PostMapping("/edit/{screenwriterId}")
    public RedirectView edit(Model model, @PathVariable int screenwriterId, @ModelAttribute Screenwriter screenwriter, @RequestParam("fileImage") MultipartFile image) throws IOException {
        screenwriter.setId(screenwriterId);
        String fileName = imageUploadHelper.saveImage("screenwriters", image);
        screenwriter.setImage(fileName);
        screenwriterService.updateScreenwriter(screenwriter);
        return new RedirectView("/screenwriters", false);
    }

    @GetMapping("/show/{screenwriterId}")
    public String show(Model model, @PathVariable int screenwriterId) {
        Screenwriter s = screenwriterService.getScreenwriterById(screenwriterId);
        List<Movie> movies = movieService.getMoviesByScreenwriter(screenwriterId);

        model.addAttribute("screenwriter", s);
        model.addAttribute("movies", movies);

        return "screenwriters/screenwriter_profile";
    }

    @PostMapping("/delete/{screenwriterId}")
    public void delete(Model model, @PathVariable int screenwriterId) {
        screenwriterService.deleteScreenwriter(screenwriterId);
    }

}
